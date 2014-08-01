package ntu.asu.rduboveckij.api.similarity;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.external.PrimitiveEnum;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Arrays;

public class DataTypeSimilarityServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SimilaritySettings settings;
    @Inject
    private DataTypeSimilarityService dataTypeSimilarityService;

    @Test
    public void testSimilarity() throws Exception {
        Model.Attribute sourceAttr = new Model.Attribute("userId", DataType.valueOf(PrimitiveEnum.LONG.getName()));
        Model.Element sourceElem = new Model.Element("userBasket", Sets.newHashSet(sourceAttr));
        Split.Element sourceSplit = new Split.Element(sourceElem, Arrays.asList("user", "basket"),
                Sets.newHashSet(new Split.Attribute(sourceAttr, Arrays.asList("user", "id"))));

        Model.Attribute targetAttr = new Model.Attribute("customerId", DataType.valueOf(PrimitiveEnum.INTEGER.getName()));
        Model.Element targetElem = new Model.Element("customer", Sets.newHashSet(targetAttr));
        Split.Element targetSplit = new Split.Element(targetElem, Arrays.asList("customer"),
                Sets.newHashSet(new Split.Attribute(targetAttr, Arrays.asList("customer", "id"))));

        double resultScore = CommonUtils.normal(Pair.of(CommonUtils.MAX_SCORE, settings.getImportanceAttributeFactor()));
        Result.Element expectedElem = new Result.Element(sourceElem, targetElem, resultScore, Sets.newHashSet());
        Assert.assertEquals(expectedElem, dataTypeSimilarityService.similarity(sourceSplit, targetSplit));
    }
}