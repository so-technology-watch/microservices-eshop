package fr.sogeti;

import fr.sogeti.consul.ConsulServiceDiscovery;
import fr.sogeti.filters.pre.PreFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@EnableHystrix
@SpringBootApplication
@EnableZuulProxy
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		new ConsulServiceDiscovery("http://10.226.159.191", 8500).getServices();
		// SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public PreFilter simpleFilter() {
		return new PreFilter();
	}
}
