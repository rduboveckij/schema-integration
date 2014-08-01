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
}