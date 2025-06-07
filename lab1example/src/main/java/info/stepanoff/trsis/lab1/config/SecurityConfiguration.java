/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Pavel Stepanov
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
        http.sessionManagement((configurer) -> {
            configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                    ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)
                                            requests.requestMatchers(new String[]{"/**"})).permitAll()
                                            .requestMatchers(new String[]{"/api/v*/**"})).permitAll()
                            .anyRequest()).denyAll();
        });

        return (SecurityFilterChain)http.build();
    }
}