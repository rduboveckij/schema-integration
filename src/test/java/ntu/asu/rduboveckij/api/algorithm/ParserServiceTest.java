package ntu.asu.rduboveckij.api.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.model.external.Model;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class ParserServiceTest extends ApplicationConfigurationTest {
    private static final String TEST_FOLDER = "src/test/resources/";

    @Inject
    private ParserService parserService;

    @Test
    public void testEasy() throws Exception {
        Model expected = new Model("parser-easy.xsd", Sets.newHashSet(
                new Model.Element("user", Sets.newHashSet(), null),
                new Model.Element("authorisation", Sets.newHashSet(), null)
        ));
        Assert.assertEquals("Elements did not parse correctly.", expected.getElements(),
                parserService.parse(TEST_FOLDER + "parser-easy.xsd").getElements());
    }

    @Test
    public void testHard() throws Exception {
        Model model = parserService.parse(TEST_FOLDER + "parser-hard.xsd");
        Set<Model.Element> elements = model.getElements();
        Assert.assertEquals("It is too much elements.", elements.size(), 2);
        List<Model.Element> elementList = Lists.newArrayList(elements);
        Assert.assertEquals("Base object is not same.", elementList.get(0).getExtend(), elementList.get(1).getExtend());
    }
}