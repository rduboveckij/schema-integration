package ntu.asu.rduboveckij;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author andrus.god
 * @since 15.12.2014.
 */
@Configuration
@EnableAutoConfiguration
@Import(ApplicationConfiguration.class)
@ComponentScan
public class WebApplicationRun extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationRun.class, args);
    }

   /* @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplicationRun.class);
    }
}
