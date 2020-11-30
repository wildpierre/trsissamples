package info.stepanoff.trsis.samples.config;

import info.stepanoff.trsis.samples.service.MyLogin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Pavel
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public String getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {                    
                    MyLogin principal = (MyLogin)authentication.getPrincipal();                    
                    return principal.getUsername();
                } else {
                    return "Unknown";
                }
            }
        };
    }
}