package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SplitterDataTypeServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SplitterService splitterService;

    @Test
    public void testSplitter() throws Exception {
        assertEquals(8, splitterService.splitter(" HelloMy,name is-Bob_i.from  USA").size());
    }
}