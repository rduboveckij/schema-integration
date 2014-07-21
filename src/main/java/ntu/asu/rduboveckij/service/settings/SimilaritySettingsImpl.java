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
    @Value("#{environment['similarity.semantic.attribute']}")
    private double semanticAttributeFactor;

    public double getSemanticAttributeFactor() {
        return semanticAttributeFactor;
    }

    public void setSemanticAttributeFactor(double semanticAttributeFactor) {
        this.semanticAttributeFactor = semanticAttributeFactor;
    }
}
