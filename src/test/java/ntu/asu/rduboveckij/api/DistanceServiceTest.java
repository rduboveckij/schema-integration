package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
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
}