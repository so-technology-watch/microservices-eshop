
package fr.sogeti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RestController;


@EnableHystrixDashboard
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@RestController
public class GatewayApplication {
    
	public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
	}
}
