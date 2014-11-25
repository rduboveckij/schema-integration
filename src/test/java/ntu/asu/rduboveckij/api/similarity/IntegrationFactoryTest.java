package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.model.internal.Mapping;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

/**
 * @author andrus.god
 * @since 22/10/2014
 */
public class IntegrationFactoryTest extends ApplicationConfigurationTest {
    private static final String TEST_FOLDER = "src/test/resources/";
    @Inject
    private IntegrationFactory integrationFactory;

    @Test
    public void testSmall() throws Exception {
        IntegrationFactory.Service service = integrationFactory
                .parse(TEST_FOLDER + "test1-first.xsd", TEST_FOLDER + "test1-second.xsd");
        Assert.assertEquals(1d, service.metricCompleteness(), 0.001);
    }
}