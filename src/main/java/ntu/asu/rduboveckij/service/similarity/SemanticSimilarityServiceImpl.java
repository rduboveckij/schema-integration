package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.SemanticSimilarityService;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Service
public class SemanticSimilarityServiceImpl implements SemanticSimilarityService {
    @Inject
    private SimilaritySettings settings;
    @Inject
    private DistanceService distanceService;

    @Override
    public Result.Element similarity(Split.Element source, Split.Element target) {
        double score = calcAverageBestDistance(source.getNames(), target.getNames());
        Set<Result.Attribute> attributes = source.getAttributes()
                .parallelStream()
                .flatMap(sa -> target.getAttributes().parallelStream()
                        .map(ta -> similarity(sa, ta)))
                .collect(Collectors.toSet());
        // attributes
        return new Result.Element(source.getParent(), target.getParent(), score, attributes);
    }

    private <T extends Result> Set<T> similarityFilterResult(Set<T> results) {

        //results.parallelStream().sorted(result -> result.getScore() >);
        return null;
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
                .mapToDouble(secondName -> distanceService.similarity(firstName, secondName))
                .max()
                .getAsDouble();
    }

    private Result.Attribute similarity(Split.Attribute source, Split.Attribute target) {
        return new Result.Attribute(source.getParent(), target.getParent(), calcAverageBestDistance(source.getNames(), target.getNames()));
    }
}