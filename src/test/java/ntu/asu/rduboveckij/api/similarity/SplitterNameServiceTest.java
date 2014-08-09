package ntu.asu.rduboveckij.api.similarity;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
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
    private DictionaryService dictionaryService;
    @Mock
    private SplitterTermService splitterTermService;

    @Test
    public void testSplit() throws Exception {
        String elementName = "userBasket";
        List<String> elementNames = Arrays.asList("user", "basket");
        Mockito.when(splitterTermService.split(elementName)).thenReturn(elementNames);

        String attributeName = "userFirstNameId";
        List<String> attributeNames = Arrays.asList("user", "first", "name", "id");
        Mockito.when(splitterTermService.split(attributeName)).thenReturn(attributeNames);
        Mockito.when(dictionaryService.isContain(Mockito.anyString())).thenReturn(false);
        Mockito.when(dictionaryService.isContain("user")).thenReturn(true);
        Mockito.when(dictionaryService.isContain("basket")).thenReturn(true);
        Mockito.when(dictionaryService.isContain("first name")).thenReturn(true);
        Mockito.when(dictionaryService.isContain("id")).thenReturn(true);

        Model.Attribute attribute = new Model.Attribute(attributeName, DataType.valueOf(PrimitiveEnum.LONG.getName()));
        Model.Element element = new Model.Element(elementName, Sets.newHashSet(attribute), null);
        Split.Attribute expectedAttribute = new Split.Attribute(attribute, Arrays.asList("user", "first name", "id"));
        Split.Element expectedElement = new Split.Element(element, elementNames, Sets.newHashSet(expectedAttribute));
        Assert.assertEquals(expectedElement, splitterNameService.split(element));
    }
}