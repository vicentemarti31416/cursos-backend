package vicente.marti.microserviciogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioGatewayApplication.class, args);
	}

}
