package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.DataTypeSimilarityService;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.model.internal.TableIndex;
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
        Set<Result.Attribute> notFiltered = CommonUtils.eachStream(source.getAttributes(), target.getAttributes(), this::similarityAttribute)
                .collect(Collectors.toSet());
        Set<Result.Attribute> attributes = CommonUtils.similarityFilter(notFiltered)
                .parallelStream()
                .filter(result -> result.getScore() > settings.getThresholdFactor())
                .collect(Collectors.toSet());
        double attributeScore = attributes.parallelStream()
                .mapToDouble(Result::getScore)
                .average().getAsDouble();
        double resultScore = CommonUtils.normal(Pair.ofOne(attributeScore));
        return new Result.Element(TableIndex.of(source, target), resultScore, attributes);
    }

    private Result.Attribute similarityAttribute(Split.Attribute source, Split.Attribute target) {
        return new Result.Attribute(TableIndex.of(source, target), similarityType(source.getParent().getType(), target.getParent().getType()));
    }

    private double similarityType(DataType sourceType, DataType targetType) {
        if (sourceType.equals(targetType)) return CommonUtils.MAX_SCORE;
        else if (sourceType.isPrimitive() && targetType.isPrimitive() &&
                DataType.isCast(sourceType.asPrimitive(), sourceType.asPrimitive()))
            return settings.getDataTypeAttributeFactor();
        else return CommonUtils.MIN_SCORE;
    }
}