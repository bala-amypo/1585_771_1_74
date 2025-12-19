import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Often disabled for local development/APIs
            .authorizeHttpRequests(auth -> auth
                // Permit all Swagger and OpenAPI related endpoints
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                // Secure all other endpoints
                .anyRequest().authenticated()
            )
            // If you still want to use login for other pages, keep this:
            .formLogin(form -> form.permitAll()); 

        return http.build();
    }
}