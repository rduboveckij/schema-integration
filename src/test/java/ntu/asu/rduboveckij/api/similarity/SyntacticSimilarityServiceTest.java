package ntu.asu.rduboveckij.api.similarity;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import ntu.asu.rduboveckij.service.settings.SimilaritySettingsImpl;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

public class SyntacticSimilarityServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SimilaritySettings settings;
    @Inject
    private SyntacticSimilarityService syntacticSimilarityService;

    @Test
    public void testSimilarity() throws Exception {
        double resultScore = CommonUtils.normal(Pair.ofOne(0.375), Pair.of(settings.getImportanceAttributeFactor(), 0.75));
        Result.Element expectedElem = new Result.Element(TableIndex.of(SPLIT_USER_BASKET, SPLIT_CUSTOMER), resultScore, Sets.newHashSet());
        Result.Element resultElem = syntacticSimilarityService.similarity(SPLIT_USER_BASKET, SPLIT_CUSTOMER);
        Assert.assertEquals(expectedElem, resultElem);
        Assert.assertEquals(expectedElem.getScore(), resultElem.getScore(), 0.001);
    }
}