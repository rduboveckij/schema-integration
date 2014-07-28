package ntu.asu.rduboveckij.api.settings;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface AlgorithmSettings {
    int getReplaceCost();

    int getReplaceCaseCost();

    int getInsertCost();

    int getRemoveCost();

    int getWordNetDepth();
}