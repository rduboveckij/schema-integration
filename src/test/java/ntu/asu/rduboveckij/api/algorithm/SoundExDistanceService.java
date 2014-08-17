package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.service.algorithm.syntatic.SyntacticAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author andrus.god
 * @since 8/15/2014
 */
public class SoundExDistanceService extends ApplicationConfigurationTest {
    @Inject
    @Named(SyntacticAlgorithm.SOUND_EX)
    private DistanceService distanceService;

    @Test
    public void testSimilarity() throws Exception {
        Assert.assertEquals(0.75, distanceService.similarity("user", "users"), 0.01);
    }
}