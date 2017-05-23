package fr.sogeti.amqp;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import fr.sogeti.security.SecurityPropertiesResolver;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 *
 * @author fduneau
 */
@Component
public class ProductsFilter extends ZuulFilter {

    private final SecurityPropertiesResolver config;
    private AmqpClient client;
    private static final Logger LOG = Logger.getLogger(ProductsFilter.class.getName());
    
    public ProductsFilter(SecurityPropertiesResolver config){
        this.config = config;
        try {
            createClient();
        }catch(IOException | TimeoutException e){
            LOG.log(Level.SEVERE, "Impossible to create on AMQP server ! {0}", e.getMessage());
        }
    }
    
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        if(request.getRequestURI().equals("/api/v1/products") && request.getMethod().equals("PUT")){
            reconnectIfNotConnected();
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            try{
                String message = requestWrapper.getReader().lines().collect(Collectors.joining(""));
                client.publish(getExchange(), getExchangeType(), getRoutingKey(), message);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    private void createClient() throws IOException, TimeoutException{
        String host = config.getProperty("rabbitmq.host");
        String user = config.getProperty("rabbitmq.user");
        String password = config.getProperty("rabbitmq.password");
        String virtualHost = config.getProperty("rabbitmq.virtualHost");
        int port = Integer.valueOf(config.getProperty("rabbitmq.port"));
        
        client = new AmqpClient(host, user, password, virtualHost, port);
    }
    
    private String getExchange(){
        return config.getProperty("rabbitmq.exchange");
    }

    private String getExchangeType(){
        return config.getProperty("rabbitmq.exchangeType");
    }    
    
    private String getRoutingKey(){
        return config.getProperty("rabbitmq.routingKey");
    }
    
    public void reconnectIfNotConnected(){
        while(!client.isOpen()){
            LOG.log(Level.WARNING, "Client not connected ... reconnecting");
            try{
                createClient();
            }catch(IOException | TimeoutException e){
                LOG.log(Level.SEVERE, "Impossible to create the Amqp client ! {0}", e.getMessage());
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        }
    }
}
