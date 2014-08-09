package ntu.asu.rduboveckij.service.settings;

import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Component
public class SimilaritySettingsImpl implements SimilaritySettings {
    @Value("#{environment['similarity.attribute.importance']}")
    private double importanceAttributeFactor;
    @Value("#{environment['similarity.dictionary.element']}")
    private double dictionaryElementFactor;
    @Value("#{environment['similarity.type.attribute']}")
    private double dataTypeAttributeFactor;
    @Value("#{environment['similarity.syntactic']}")
    private double syntacticFactor;
    @Value("#{environment['similarity.dictionary']}")
    private double dictionaryFactor;
    @Value("#{environment['similarity.type']}")
    private double dataTypeFactor;
    @Value("#{environment['similarity.threshold']}")
    private double thresholdFactor;

    @Override
    public double getImportanceAttributeFactor() {
        return importanceAttributeFactor;
    }

    public void setImportanceAttributeFactor(double importanceAttributeFactor) {
        this.importanceAttributeFactor = importanceAttributeFactor;
    }

    @Override
    public double getDictionaryElementFactor() {
        return dictionaryElementFactor;
    }

    public void setDictionaryElementFactor(double dictionaryElementFactor) {
        this.dictionaryElementFactor = dictionaryElementFactor;
    }

    @Override
    public double getDataTypeAttributeFactor() {
        return dataTypeAttributeFactor;
    }

    public void setDataTypeAttributeFactor(double dataTypeAttributeFactor) {
        this.dataTypeAttributeFactor = dataTypeAttributeFactor;
    }
    @Override
    public double getSyntacticFactor() {
        return syntacticFactor;
    }

    public void setSyntacticFactor(double syntacticFactor) {
        this.syntacticFactor = syntacticFactor;
    }
    @Override
    public double getDictionaryFactor() {
        return dictionaryFactor;
    }

    public void setDictionaryFactor(double dictionaryFactor) {
        this.dictionaryFactor = dictionaryFactor;
    }
    @Override
    public double getDataTypeFactor() {
        return dataTypeFactor;
    }

    public void setDataTypeFactor(double dataTypeFactor) {
        this.dataTypeFactor = dataTypeFactor;
    }
    @Override
    public double getThresholdFactor() {
        return thresholdFactor;
    }

    public void setThresholdFactor(double thresholdFactor) {
        this.thresholdFactor = thresholdFactor;
    }
}