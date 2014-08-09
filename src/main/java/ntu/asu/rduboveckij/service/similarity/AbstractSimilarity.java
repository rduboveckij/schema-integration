package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.SimilarityService;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public abstract class AbstractSimilarity implements SimilarityService {
    @Inject
    protected SimilaritySettings settings;

    @Override
    public Result.Element similarity(Split.Element source, Split.Element target) {
        double elementScore = calcAverageBestDistance(source.getNames(), target.getNames());
        Set<Result.Attribute> notFiltered = CommonUtils.eachStream(source.getAttributes(), target.getAttributes(), this::similarityAttribute)
                .collect(Collectors.toSet());
        Set<Result.Attribute> attributes = CommonUtils.similarityFilter(notFiltered)
                .parallelStream()
                .filter(result -> result.getScore() > settings.getThresholdFactor())
                .collect(Collectors.toSet());
        double attributeScore = attributes.parallelStream()
                .mapToDouble(Result::getScore)
                .average().getAsDouble();
        double resultScore = CommonUtils.normal(Pair.ofOne(elementScore), Pair.of(settings.getImportanceAttributeFactor(), attributeScore));
        return new Result.Element(TableIndex.of(source, target), resultScore, attributes);
    }



    private double calcAverageBestDistance(List<String> sourceNames, List<String> targetNames) {
        DoubleStream result = sourceNames.size() < targetNames.size() ?
                calcBestDistance(targetNames, sourceNames) :
                calcBestDistance(sourceNames, targetNames);
        return result.average()
                .getAsDouble();
    }

    private DoubleStream calcBestDistance(List<String> firstNames, List<String> secondNames) {
        return firstNames.parallelStream()
                .mapToDouble(firstName -> findBestDistance(firstName, secondNames));
    }

    private double findBestDistance(String firstName, List<String> secondNames) {
        return secondNames.parallelStream()
                .mapToDouble(secondName -> getScore(firstName, secondName))
                .max()
                .getAsDouble();
    }

    protected abstract double getScore(String firstName, String secondName);

    private Result.Attribute similarityAttribute(Split.Attribute source, Split.Attribute target) {
        return new Result.Attribute(TableIndex.of(source, target), calcAverageBestDistance(source.getNames(), target.getNames()));
    }
}