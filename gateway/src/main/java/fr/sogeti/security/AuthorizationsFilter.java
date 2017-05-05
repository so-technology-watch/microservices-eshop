package fr.sogeti.security;

import java.io.IOException;
import static java.lang.String.format;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fduneau
 */
@Component
public class AuthorizationsFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthorizationsFilter.class.getName());
    private final String authRoute;
    private final RestTemplate rest;
    private final SecurityPropertiesResolver config;
    
    public AuthorizationsFilter(SecurityPropertiesResolver config){
        rest = new RestTemplate();
        this.config = config;
        authRoute = String.format("http://localhost:%s/api/v1/auth", config.getProperty("server.port"));
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String prefix = config.getZuulPrefix();
            String uri = request.getRequestURI();
            if(uri.contains(prefix)){
                uri = uri.replaceAll(prefix, "");
            }
            
            if(config.isSecured(uri, request.getMethod())){
                boolean authorized = isAuthorized(request.getHeader("Authorization"));
                if(LOG.isLoggable(Level.INFO)){
                    LOG.log(Level.INFO, "Request needing authorization on route : {0}", uri);
                    LOG.log(Level.INFO, "Authorized : {0}", authorized);
                }
                
                if(!authorized){
                    HttpServletResponse response = (HttpServletResponse) servletResponse;
                    response.sendError(401, "You are not authorized to access this ressource");
                }
            }else{
                LOG.log(Level.INFO, "Normal request on {0}", uri);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
    
    /**
     * 
     * @return 
     */
    private boolean isAuthorized(String token){
        if(Objects.isNull(token)){
            return false;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response;
        
        try{
            response = rest.exchange(format(authRoute, token), HttpMethod.GET, entity, String.class);
        }catch(HttpServerErrorException e){
            LOG.log(Level.SEVERE, "Error during requesting authorization : {0}", e.getMessage());
            return false;
        }
        System.out.println(format("response %s", response));
        if(response.getStatusCode() != HttpStatus.OK){
            return false;
        }
        System.out.println("is authorized");
        return true;
    }
}
