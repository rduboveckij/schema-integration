package ntu.asu.rduboveckij.service.settings;

import com.google.common.collect.Range;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.util.CommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Component
public class SimilaritySettingsImpl implements SimilaritySettings {
    private volatile double importanceAttributeFactor;
    private volatile double dictionaryElementFactor;
    private volatile double dataTypeAttributeFactor;
    private volatile double syntacticFactor;
    private volatile double dictionaryFactor;
    private volatile double dataTypeFactor;
    private volatile double thresholdFactor;

    @Override
    public double getImportanceAttributeFactor() {
        return importanceAttributeFactor;
    }

    @Value("#{environment['similarity.attribute.importance']}")
    public void setImportanceAttributeFactor(double importanceAttributeFactor) {
        this.importanceAttributeFactor = CommonUtils.requireRange(importanceAttributeFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getDictionaryElementFactor() {
        return dictionaryElementFactor;
    }

    @Value("#{environment['similarity.dictionary.element']}")
    public void setDictionaryElementFactor(double dictionaryElementFactor) {
        this.dictionaryElementFactor = CommonUtils.requireRange(dictionaryElementFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getDataTypeAttributeFactor() {
        return dataTypeAttributeFactor;
    }

    @Value("#{environment['similarity.type.attribute']}")
    public void setDataTypeAttributeFactor(double dataTypeAttributeFactor) {
        this.dataTypeAttributeFactor = CommonUtils.requireRange(dataTypeAttributeFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getSyntacticFactor() {
        return syntacticFactor;
    }

    @Value("#{environment['similarity.syntactic']}")
    public void setSyntacticFactor(double syntacticFactor) {
        this.syntacticFactor = CommonUtils.requireRange(syntacticFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getDictionaryFactor() {
        return dictionaryFactor;
    }

    @Value("#{environment['similarity.dictionary']}")
    public void setDictionaryFactor(double dictionaryFactor) {
        this.dictionaryFactor = CommonUtils.requireRange(dictionaryFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getDataTypeFactor() {
        return dataTypeFactor;
    }

    @Value("#{environment['similarity.type']}")
    public void setDataTypeFactor(double dataTypeFactor) {
        this.dataTypeFactor = CommonUtils.requireRange(dataTypeFactor, Range.closed(0d, 1d));
    }

    @Override
    public double getThresholdFactor() {
        return thresholdFactor;
    }

    @Value("#{environment['similarity.threshold']}")
    public void setThresholdFactor(double thresholdFactor) {
        this.thresholdFactor = CommonUtils.requireRange(thresholdFactor, Range.closed(0d, 1d));
    }
}