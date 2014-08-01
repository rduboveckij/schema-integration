package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.DataTypeSimilarityService;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 7/28/2014
 */
@Service
public class DataTypeSimilarityServiceImpl implements DataTypeSimilarityService {
    @Inject
    protected SimilaritySettings settings;

    @Override
    public Result.Element similarity(Split.Element source, Split.Element target) {
        Set<Result.Attribute> notFiltered = source.getAttributes()
                .parallelStream()
                .flatMap(sa -> target.getAttributes().parallelStream()
                        .map(ta -> similarityAttribute(sa, ta)))
                .collect(Collectors.toSet());
        Set<Result.Attribute> attributes = CommonUtils.similarityFilter(notFiltered);
        double attributeScore = attributes.stream()
                .mapToDouble(Result::getScore)
                .average().getAsDouble();

        double resultScore = CommonUtils.normal(Pair.of(CommonUtils.MAX_SCORE, attributeScore));
        return new Result.Element(source.getParent(), target.getParent(), resultScore, attributes);
    }

    private Result.Attribute similarityAttribute(Split.Attribute source, Split.Attribute target) {
        Model.Attribute parentSource = source.getParent();
        Model.Attribute parentTarget = target.getParent();
        return new Result.Attribute(parentSource, parentTarget, similarityType(parentSource.getType(), parentTarget.getType()));
    }

    private double similarityType(DataType sourceType, DataType targetType) {
        if (sourceType.equals(targetType)) return CommonUtils.MAX_SCORE;
        else if (sourceType.isPrimitive() && targetType.isPrimitive() &&
                DataType.isCast(sourceType.asPrimitive(), sourceType.asPrimitive()))
            return settings.getDataTypeAttributeFactor();
        else return CommonUtils.MIN_SCORE;
    }
}