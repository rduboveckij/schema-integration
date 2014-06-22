package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SplitterDataTypeServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SplitterService splitterService;

    @Test
    public void testSplitter() throws Exception {

        List<String> splitter = splitterService.splitter(" HelloMy,name is-Bob_i.from  USA");
        System.out.println(splitter + " " + splitter.size());
        assertTrue(true);
    }
}