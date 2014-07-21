package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface SemanticSimilarityService {
     Result.Element similarity(Split.Element source, Split.Element target);
}
