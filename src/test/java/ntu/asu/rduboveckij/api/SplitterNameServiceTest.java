package ntu.asu.rduboveckij.api;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.model.external.*;
import ntu.asu.rduboveckij.model.internal.AttributeSplit;
import ntu.asu.rduboveckij.model.internal.ElementSplit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class SplitterNameServiceTest extends ApplicationConfigurationTest {
    @InjectMocks
    @Inject
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

        Attribute attribute = new Attribute(attributeName, DataType.valueOf(PrimitiveEnum.LONG.getName()));
        Element element = new Element(elementName, Sets.newHashSet(attribute));
        ElementSplit expectedElement = new ElementSplit(element, elementNames, Sets.newHashSet(new AttributeSplit(attribute, attributeNames)));
        Assert.assertEquals(expectedElement, splitterNameService.split(element));
    }
}