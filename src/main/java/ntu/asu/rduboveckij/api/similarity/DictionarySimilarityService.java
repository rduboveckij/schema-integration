package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.internal.Split;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public interface DictionarySimilarityService extends SimilarityService {
    double metricCompleteness(Split.Element element);
}