package ntu.asu.rduboveckij.api.algorithm;

/**
 * @author andrus.god
 * @since 6/29/2014
 */
public interface DictionaryService {
    boolean isSynonym(String first, String second);

    boolean isContain(String term);
}