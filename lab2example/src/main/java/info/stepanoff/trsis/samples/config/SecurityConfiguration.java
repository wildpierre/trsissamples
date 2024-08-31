/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Pavel Stepanov
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/**", "/css/**", "/js/**", "/images/**", "/public/rest/**").permitAll()
                );

        http.exceptionHandling((exception) -> exception.accessDeniedPage("/403"));
        http.csrf().disable();

        return http.build();
    }
}
