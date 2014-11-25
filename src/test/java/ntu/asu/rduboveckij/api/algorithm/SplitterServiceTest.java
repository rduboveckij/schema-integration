package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.algorithm.SplitterTermService;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SplitterServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SplitterTermService splitterTermService;

    @Test
    public void testSplitter() throws Exception {
        assertEquals(8, splitterTermService.split("helloMy,name is-Bob_i.from  USA").size());
        assertEquals(2, splitterTermService.split("customerId").size());
        assertEquals(2, splitterTermService.split("user_id").size());
        assertEquals(2, splitterTermService.split("CreditCard").size());
        assertEquals(2, splitterTermService.split("creditCard").size());
    }
}