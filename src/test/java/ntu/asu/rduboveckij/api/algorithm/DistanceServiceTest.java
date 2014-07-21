package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

public class DistanceServiceTest extends ApplicationConfigurationTest {
    @Inject
    private DistanceService distanceService;

    @Test
    public void testDistance() throws Exception {
        Assert.assertEquals(1, distanceService.distance("user", "users"));
    }

    @Test
    public void testSimilarity() throws Exception {
        Assert.assertEquals(0.8, distanceService.similarity("user", "users"), 0.1);
    }
}