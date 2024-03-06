package schoolManagement.mg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfiguration {

    @Bean
     JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}
