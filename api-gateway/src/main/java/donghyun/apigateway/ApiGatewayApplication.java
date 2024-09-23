package donghyun.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("word-generator", r -> r.path("/api/v1/word-generator/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8081"))
                .route("word-processor", r -> r.path("/api/v1/word-processor/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8082"))
                .route("notification-service", r -> r.path("/api/v1/notifications/**")
                        .uri("http://localhost:8083"))
                .build();
    }

}
