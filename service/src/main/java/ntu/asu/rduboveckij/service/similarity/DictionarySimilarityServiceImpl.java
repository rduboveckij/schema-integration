package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
import ntu.asu.rduboveckij.api.similarity.DictionarySimilarityService;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
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

    @Override
    public double metricCompleteness(Split.Element element) {
        double attributeScore = element.getAttributes().parallelStream()
                .map(Split::getNames)
                .mapToDouble(this::containAverages)
                .average()
                .orElse(CommonUtils.MIN_SCORE);
        return CommonUtils.normal(Pair.ofOne(containAverages(element.getNames())),
                Pair.of(settings.getImportanceAttributeFactor(), attributeScore));
    }

    private double containAverages(List<String> terms) {
        return terms.parallelStream()
                .map(dictionaryService::isContain)
                .mapToDouble(CommonUtils::booleanToDouble)
                .average()
                .orElse(CommonUtils.MIN_SCORE);
    }
}