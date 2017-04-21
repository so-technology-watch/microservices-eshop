
package fr.sogeti;

import fr.sogeti.filters.pre.PreFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

    
    
    @Autowired
    private DiscoveryClient discoveryClient;
	public static void main(String[] args) {
        //new ConsulServiceDiscovery("http://10.226.159.191", 8500).getServices();
        SpringApplication.run(GatewayApplication.class, args);
        //System.out.println(discovery);
	}
    
    @Bean
    public PreFilter simpleFilter() {
        return new PreFilter();
    }
    
    /*
    
    @Bean
    public PreDecorationFilter preDecorationFilter() {
        return new PreDecorationFilter();
    }

    */
}
>>>>>>> 69eb2ec6621df364650e5a998f4440105af27b7b
