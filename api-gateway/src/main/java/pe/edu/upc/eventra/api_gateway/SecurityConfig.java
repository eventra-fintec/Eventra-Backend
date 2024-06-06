package pe.edu.upc.eventra.api_gateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Deshabilita CSRF
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                //"/**",
                                "/api/**",
                                "/v3/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/tickets-service/v3/api-docs",
                                "/user-service/v3/api-docs",
                                "/events-service/v3/api-docs",
                                "/reservations-service/v3/api-docs",
                                "/notification-service/v3/api-docs",
                                "/payment-service/v3/api-docs"
                        ).permitAll() // Permitir acceso a Swagger y API docs
                        .anyExchange().authenticated() // Requerir autenticación para cualquier otra ruta
                ); // Configura autenticación básica

        return http.build();
    }
}
