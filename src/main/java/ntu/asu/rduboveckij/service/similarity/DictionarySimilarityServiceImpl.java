package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
import ntu.asu.rduboveckij.api.similarity.DictionarySimilarityService;
import ntu.asu.rduboveckij.util.CommonUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Objects;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
@Service
public class DictionarySimilarityServiceImpl extends AbstractSimilarity implements DictionarySimilarityService {
    @Inject
    private DictionaryService dictionaryService;

    @Override
    protected double getScore(String firstName, String secondName) {
        if (Objects.equals(firstName, secondName)) return CommonUtils.MAX_SCORE;
        if (dictionaryService.isSynonym(firstName, secondName)) return settings.getDictionaryElementFactor();
        return CommonUtils.MIN_SCORE;
    }
}