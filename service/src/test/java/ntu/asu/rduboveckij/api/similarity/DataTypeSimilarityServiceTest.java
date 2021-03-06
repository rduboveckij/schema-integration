package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;

public class DataTypeSimilarityServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SimilaritySettings settings;
    @Inject
    private DataTypeSimilarityService dataTypeSimilarityService;

    @Test
    public void testSimilarity() throws Exception {
        double resultScore = CommonUtils.normal(Pair.of(CommonUtils.MAX_SCORE, settings.getDataTypeAttributeFactor()));
        Result.Element resultElem = dataTypeSimilarityService.similarity(SPLIT_USER_BASKET, SPLIT_CUSTOMER);
        Assert.assertEquals(resultScore, resultElem.getScore(), 0.001);
    }
}