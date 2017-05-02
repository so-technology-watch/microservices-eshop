
package fr.sogeti;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

//@EnableHystrixDashboard
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
//@EnableOAuth2Resource
@EnableOAuth2Sso
@EnableOAuth2Client
@RestController
@Service
public class GatewayApplication {
    
	public static void main(String[] args) {
        String consulAddress = System.getenv("CONSUL_CLIENT");
        if(Objects.isNull(consulAddress)){
            System.err.println("Please set the CONSUL_CLIENT environment variable properly");
            return;
        }
        
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        String consulHost = "--spring.cloud.consul.host";
        
        if(arguments.stream().noneMatch(s -> s.contains(consulHost))){
            arguments.add(format("%s=%s", consulHost, consulAddress));
        }
        
        args = arguments.toArray(new String[arguments.size()]);
        ConfigurableApplicationContext spring = SpringApplication.run(GatewayApplication.class, args);
	}
}
