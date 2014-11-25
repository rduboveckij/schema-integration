package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

public class DictionaryServiceTest extends ApplicationConfigurationTest {
    @Inject
    private DictionaryService dictionaryService;

    @Test
    @Ignore
    public void testIsSynonym() throws Exception {
        Assert.assertTrue(dictionaryService.isSynonym("funny", "droll"));
    }

    @Test
    public void testIsContain() throws Exception {
        Assert.assertTrue(dictionaryService.isContain("droll"));
        Assert.assertTrue(dictionaryService.isContain("updated"));
    }
}