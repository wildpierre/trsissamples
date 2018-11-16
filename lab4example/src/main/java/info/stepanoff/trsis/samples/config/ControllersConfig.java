/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ControllersConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        
        registry.addViewController("/schools").setViewName("schools");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/forbidden").setViewName("forbidden");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("schools");

    }

}
