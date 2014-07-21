package ntu.asu.rduboveckij.api.similarity;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.similarity.SplitterNameService;
import ntu.asu.rduboveckij.api.algorithm.SplitterTermService;
import ntu.asu.rduboveckij.model.external.*;
import ntu.asu.rduboveckij.model.internal.Split;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class SplitterNameServiceTest extends ApplicationConfigurationTest {
    @Inject
    @InjectMocks
    private SplitterNameService splitterNameService;
    @Mock
    private SplitterTermService splitterTermService;

    @Test
    public void testSplit() throws Exception {
        String elementName = "userBasket";
        List<String> elementNames = Arrays.asList("user", "basket");
        Mockito.when(splitterTermService.split(elementName)).thenReturn(elementNames);

        String attributeName = "userId";
        List<String> attributeNames = Arrays.asList("user", "id");
        Mockito.when(splitterTermService.split(attributeName)).thenReturn(attributeNames);

        Model.Attribute attribute = new Model.Attribute(attributeName, DataType.valueOf(PrimitiveEnum.LONG.getName()));
        Model.Element element = new Model.Element(elementName, Sets.newHashSet(attribute));
        Split.Element expectedElement = new Split.Element(element, elementNames, Sets.newHashSet(new Split.Attribute(attribute, attributeNames)));
        Assert.assertEquals(expectedElement, splitterNameService.split(element));
    }
}