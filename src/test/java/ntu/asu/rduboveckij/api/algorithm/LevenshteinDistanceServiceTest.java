package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.service.algorithm.syntatic.SyntacticAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;

public class LevenshteinDistanceServiceTest extends ApplicationConfigurationTest {
    @Inject
    @Named(SyntacticAlgorithm.LEVENSHTEIN)
    private DistanceService distanceService;

    @Test
    public void testSimilarityEasy() throws Exception {
        Assert.assertEquals(0.8, distanceService.similarity("user", "users"), 0.01);
    }

    @Test
    public void testSimilarityMiddle() throws Exception {
        Assert.assertEquals(0.5, distanceService.similarity("user", "customer"), 0.01);
    }

    @Test
    public void testSimilarityHard() throws Exception {
        Assert.assertEquals(0.4, distanceService.similarity("uesr", "users"), 0.01);
    }

}