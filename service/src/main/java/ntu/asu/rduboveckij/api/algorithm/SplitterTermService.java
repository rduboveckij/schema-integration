package ntu.asu.rduboveckij.api.algorithm;

import java.util.List;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public interface SplitterTermService {
    List<String> split(String term);
}