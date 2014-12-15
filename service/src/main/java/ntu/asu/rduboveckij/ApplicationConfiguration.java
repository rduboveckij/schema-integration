package ntu.asu.rduboveckij;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
}