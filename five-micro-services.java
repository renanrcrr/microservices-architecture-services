// Service 1: Discovery Service (Eureka)
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }
}

// Service 2: Gateway Service (Zuul)
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}

// Service 3: Hello Service
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }
}

@RestController
class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Hello Service!";
    }
}

// Service 4: World Service
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WorldServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorldServiceApplication.class, args);
    }
}

@RestController
class WorldController {
    @GetMapping("/world")
    public String world() {
        return "Hello from World Service!";
    }
}

// Service 5: Aggregator Service
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AggregatorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AggregatorServiceApplication.class, args);
    }
}

@RestController
class AggregatorController {
    private final RestTemplate restTemplate;

    public AggregatorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/aggregate")
    public String aggregate() {
        String helloResponse = restTemplate.getForObject("http://hello-service/hello", String.class);
        String worldResponse = restTemplate.getForObject("http://world-service/world", String.class);
        return helloResponse + "\n" + worldResponse;
    }
}
