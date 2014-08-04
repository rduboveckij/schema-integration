package ntu.asu.rduboveckij.api.settings;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface SimilaritySettings {
    double getImportanceAttributeFactor();

    double getDictionaryElementFactor();

    double getDataTypeAttributeFactor();

    double getSemanticFactor();

    double getDictionaryFactor();

    double getDataTypeFactor();

    double getThresholdFactor();
}