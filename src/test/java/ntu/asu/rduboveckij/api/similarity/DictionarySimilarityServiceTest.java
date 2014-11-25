package ntu.asu.rduboveckij.api.similarity;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.ApplicationConfigurationTest;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Arrays;

/**
 * @author andrus.god
 * @since 8/3/2014
 */
public class DictionarySimilarityServiceTest extends ApplicationConfigurationTest {
    @Inject
    private SimilaritySettings settings;
    @Inject
    private DictionarySimilarityService dictionarySimilarityService;

    @Test
    public void testSimilarity() throws Exception {
        double resultScore = CommonUtils.normal(Pair.ofOne(0.0), Pair.of(settings.getImportanceAttributeFactor(), 0.5));
        Result.Element expectedElem = new Result.Element(TableIndex.of(SPLIT_USER_BASKET, SPLIT_CUSTOMER), resultScore, Sets.newHashSet());
        Result.Element resultElem = dictionarySimilarityService.similarity(SPLIT_USER_BASKET, SPLIT_CUSTOMER);
        Assert.assertEquals(expectedElem, resultElem);
        Assert.assertEquals(expectedElem.getScore(), resultElem.getScore(), 0.001);
    }

    @Test
    public void testMetricCompleteness() throws Exception {
        Assert.assertEquals("It is not correct score in element with all term is contain", 1.0,
                dictionarySimilarityService.metricCompleteness(SPLIT_USER_BASKET), 0.001);

        Split.Element expectedWithEmpty = new Split.Element(SPLIT_USER_BASKET.getParent(),
                Arrays.asList("user", "bacgket", "empty-term"), Sets.newHashSet());
        Assert.assertEquals("It is not correct score in element with term is not contain", 0.666,
                dictionarySimilarityService.metricCompleteness(expectedWithEmpty), 0.001);
    }
}