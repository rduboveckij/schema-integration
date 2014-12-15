package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.similarity.IntegrationFactory;
import org.junit.Test;

import javax.inject.Inject;

public class XsdModelGeneratorServiceImplTest extends ApplicationConfigurationTest {
    private static final String TEST_FOLDER = "src/test/resources/";
    @Inject
    private ModelGeneratorService modelGeneratorService;
    @Inject
    private IntegrationFactory integrationFactory;

    @Test
    public void testSave() throws Exception {
        IntegrationFactory.Service service = integrationFactory
                .parse(TEST_FOLDER + "test3-first.xsd", TEST_FOLDER + "test3-second.xsd");
        modelGeneratorService.generate(service.integration());
    }
}