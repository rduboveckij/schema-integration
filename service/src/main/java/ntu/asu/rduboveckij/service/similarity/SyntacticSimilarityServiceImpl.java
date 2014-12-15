package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.similarity.SyntacticSimilarityService;
import ntu.asu.rduboveckij.service.algorithm.syntatic.DistanceStrategy;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Service
public class SyntacticSimilarityServiceImpl extends AbstractSimilarity implements SyntacticSimilarityService {
    @Inject
    private DistanceStrategy distanceStrategy;

    @Override
    protected double getScore(String firstName, String secondName) {
        return distanceStrategy.similarity(firstName, secondName);
    }
}