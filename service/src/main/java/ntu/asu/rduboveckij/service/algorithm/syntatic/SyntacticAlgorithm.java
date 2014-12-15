package ntu.asu.rduboveckij.service.algorithm.syntatic;

/**
 * @author andrus.god
 * @since 8/15/2014
 */
public final class SyntacticAlgorithm {
    public static final String LEVENSHTEIN = "SyntacticAlgorithm.LEVENSHTEIN";
    public static final String DAMERAU_LEVENSHTEIN = "SyntacticAlgorithm.DAMERAU_LEVENSHTEIN";
    public static final String SOUND_EX = "SyntacticAlgorithm.SOUND_EX";

    private SyntacticAlgorithm() {
        throw new RuntimeException("This is util class.");
    }
}
