package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.service.algorithm.syntatic.SyntacticAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;

public class DamerauLevenshteinDistanceTest extends ApplicationConfigurationTest {
    @Inject
    @Named(SyntacticAlgorithm.DAMERAU_LEVENSHTEIN)
    private DistanceService distanceService;

    @Test
    public void testSimilarity() throws Exception {
        Assert.assertEquals(0.6, distanceService.similarity("uesr", "users"), 0.01);
    }
}