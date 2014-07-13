package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

public class DictionaryServiceTest extends ApplicationConfigurationTest {
    @Inject
    private DictionaryService dictionaryService;

    @Test
    public void testIsSynonym() throws Exception {
        Assert.assertTrue(dictionaryService.isSynonym("funny", "droll"));
    }

    @Test
    public void testIsContain() throws Exception {
        Assert.assertTrue(dictionaryService.isContain("droll"));
    }
}