package guru.springframework.springrestclientexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
        http.redirectToHttps()
            .httpsRedirectWhen(e -> e.getRequest().getHeaders().containsKey("X-Forwarded-Proto"))
            .and()
            .authorizeExchange()
            .anyExchange()
            .permitAll()
            .and()
            .csrf()
            .disable();

        return http.build();
    }

}
