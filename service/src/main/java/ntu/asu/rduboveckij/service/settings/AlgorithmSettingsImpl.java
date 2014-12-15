package ntu.asu.rduboveckij.service.settings;

import com.google.common.collect.Range;
import ntu.asu.rduboveckij.api.settings.AlgorithmSettings;
import ntu.asu.rduboveckij.util.CommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Component
public class AlgorithmSettingsImpl implements AlgorithmSettings {
    private volatile int replaceCost;
    private volatile int replaceCaseCost;
    private volatile int insertCost;
    private volatile int removeCost;
    private volatile int transformCost;
    private volatile String distanceStrategy;
    private volatile int wordNetDepth;

    @Override
    public int getReplaceCost() {
        return replaceCost;
    }

    @Value("#{environment['distance.levenshtein.replaceCost']}")
    public void setReplaceCost(int replaceCost) {
        this.replaceCost = CommonUtils.requireRange(replaceCost, Range.greaterThan(0));
    }

    @Override
    public int getReplaceCaseCost() {
        return replaceCaseCost;
    }

    @Value("#{environment['distance.levenshtein.replaceCaseCost']}")
    public void setReplaceCaseCost(int replaceCaseCost) {
        this.replaceCaseCost = CommonUtils.requireRange(replaceCaseCost, Range.greaterThan(0));
    }

    @Override
    public int getInsertCost() {
        return insertCost;
    }

    @Value("#{environment['distance.levenshtein.insertCost']}")
    public void setInsertCost(int insertCost) {
        this.insertCost = CommonUtils.requireRange(insertCost, Range.greaterThan(0));
    }

    @Override
    public int getRemoveCost() {
        return removeCost;
    }

    @Value("#{environment['distance.levenshtein.removeCost']}")
    public void setRemoveCost(int removeCost) {
        this.removeCost = CommonUtils.requireRange(removeCost, Range.greaterThan(0));
    }

    @Override
    public int getTransformCost() {
        return transformCost;
    }

    @Value("#{environment['distance.damerau-levenshtein.transformCost']}")
    public void setTransformCost(int transformCost) {
        this.transformCost = CommonUtils.requireRange(transformCost, Range.greaterThan(0));
    }

    @Override
    public String getDistanceStrategy() {
        return distanceStrategy;
    }

    @Value("#{environment['distance.strategy']}")
    public void setDistanceStrategy(String distanceStrategy) {
        this.distanceStrategy = CommonUtils.requireNotEmpty(distanceStrategy);
    }

    @Override
    public int getWordNetDepth() {
        return wordNetDepth;
    }

    @Value("#{environment['dictionary.wordnet.depth']}")
    public void setWordNetDepth(int wordNetDepth) {
        this.wordNetDepth = CommonUtils.requireRange(wordNetDepth, Range.atLeast(0));
    }
}