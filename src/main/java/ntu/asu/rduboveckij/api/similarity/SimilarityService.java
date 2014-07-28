package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public abstract interface SimilarityService {
    Result.Element similarity(Split.Element source, Split.Element target);
}