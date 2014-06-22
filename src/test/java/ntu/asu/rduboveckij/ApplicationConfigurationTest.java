package ntu.asu.rduboveckij;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
@ContextConfiguration(classes = ApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ApplicationConfigurationTest {

}
