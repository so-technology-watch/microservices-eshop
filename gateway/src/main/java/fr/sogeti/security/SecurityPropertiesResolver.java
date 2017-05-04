package fr.sogeti.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.AntPathMatcher;

/**
 *
 * @author fduneau
 */
@Configuration
public class SecurityPropertiesResolver implements ApplicationListener<RefreshEvent> {
    
    @Autowired
    private Environment env;    
    
    
    private Collection<AuthorizedUrl> authorizedUrls;
    private static final Logger LOG = Logger.getLogger(SecurityPropertiesResolver.class.getName());
    private final AntPathMatcher matcher;
    private volatile boolean needRefresh;
    
    public SecurityPropertiesResolver(){
        authorizedUrls = new ArrayList<>();
        matcher = new AntPathMatcher();
        needRefresh = true;
    }
    
    /**
     * Called when a refresh event on consul and other is sent
     * @param event 
     */
    @Override
    public void onApplicationEvent(RefreshEvent event) {
        needRefresh = true;
    }
    
    private void refresh(){
        authorizedUrls.clear();
        Map<String, String> matching = getPropertiesMatching("secured.*.");
        Set<String> keySet = matching.keySet();
        
        Map<String, AuthorizedUrl> urls = new HashMap<>();
        
        for(String key : keySet){
            String cle = key.replaceAll("(^(.*?)\\.)|(\\.(.*))", "");
            String value = matching.get(key);
            
            if(!urls.containsKey(cle)){
                urls.put(cle, new AuthorizedUrl());
            }
            
            if(key.contains("path")){
                urls.get(cle).setRoute(value);
            }else{
                if(key.contains("methods")){
                    urls.get(cle).addMethod(value.split(","));
                }
            }
        }
        
        authorizedUrls = urls.values();
        if(LOG.isLoggable(Level.INFO)){
            LOG.log(Level.INFO, "New urls with authorization required : ");
            authorizedUrls.forEach( u -> LOG.log(Level.INFO, u.toString()) );
        }
    }
    
    /**
     * 
     * @return the zuul's prefix
     */
    public String getZuulPrefix(){
        return env.getProperty("zuul.prefix");
    }
    
    /**
     * 
     * @param url
     * @param method
     * @return a boolean that indacted if the given url has to be secured
     */
    public boolean isSecured(String url, String method){
        if(needRefresh){
            needRefresh = !needRefresh;
            refresh();
        }
        return authorizedUrls
            .stream()
            .filter(u -> matcher.match(u.getRoute(), url))
            .anyMatch(u -> u.isAllowed(method));
    }
    
    private Map<String, String> getPropertiesMatching(String regex){
        Map<String, Object> properties = getAllKnownProperties(env);
        Map<String, String> rtn = new HashMap<>();
        properties.forEach((key,value) -> {
            if(key.matches(regex)){
                rtn.put(key, value.toString());
            }            
        });
        return rtn;
    }
    
    /**
     * #see https://jira.spring.io/browse/SPR-10241
     * @param env
     * @return all known properties in a Map
     */
    private Map<String, Object> getAllKnownProperties(Environment env) {
        Map<String, Object> rtn = new HashMap<>();
        if (env instanceof ConfigurableEnvironment) {
            for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources()) {
                if (propertySource instanceof EnumerablePropertySource) {
                    for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                        rtn.put(key, propertySource.getProperty(key));
                    }
                }
            }
        }
        return rtn;
    }

    static class AuthorizedUrl {
        String route;
        List<String> methods;
        
        public AuthorizedUrl(){
            methods = new ArrayList<>();
        }
        
        public void addMethod(String ... methods){
            this.methods.addAll(Arrays.asList(methods));
        }
        
        public void setRoute(String route){
            this.route = route;
        }

        @Override
        public String toString() {
            return "AuthorizedUrl{" + "route=" + route + ", methods=" + methods + '}';
        }
        
        public String getRoute(){
            return route;
        }
        
        /**
         * 
         * @param method
         * @return a boolean that indicates if the method is allowed, if this contains no method, all method aren't allowed
         */
        private boolean isAllowed(String method){
            if(method.isEmpty()){
                return false;
            }
            return methods.contains(method);
        }
    }
}
