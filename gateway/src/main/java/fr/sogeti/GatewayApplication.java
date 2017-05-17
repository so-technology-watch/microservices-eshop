
package fr.sogeti;

import fr.sogeti.security.AuthorizationsFilter;
import fr.sogeti.security.HeadersFilter;
import fr.sogeti.security.SecurityPropertiesResolver;
import static java.lang.String.format;
import static java.lang.System.err;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Objects.isNull;
import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.SpringApplication.run;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@EnableHystrixDashboard // dashboard hystrix
@SpringBootApplication // permettre de lancer l'app
@EnableZuulProxy // permet d'encapsulerles requetes vers les microservices dans zuul
@EnableDiscoveryClient // ajout du discovery des services 
@RestController // cette classe est un controlleur REST
public class GatewayApplication {
    @Autowired
    private SecurityPropertiesResolver config;
    
    @Bean
    @PostConstruct
    public Filter authorizationsFilter() {
        return new AuthorizationsFilter(config);
    }
    
    @Bean
    @PostConstruct
    public Filter headersFilter() {
        return new HeadersFilter();
    }
    
	public static void main(String[] args) {
        String consulAddress = System.getenv("CONSUL_CLIENT");
        if(isNull(consulAddress)){
            err.println("Please set the CONSUL_CLIENT environment variable properly");
            return;
        }
        
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        String consulHost = "--spring.cloud.consul.host";
        
        if(arguments.stream().noneMatch(s -> s.contains(consulHost))){
            arguments.add(format("%s=%s", consulHost, consulAddress));
        }
        
        args = arguments.toArray(new String[arguments.size()]);
        run(GatewayApplication.class, args);
	}
}
