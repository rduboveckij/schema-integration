package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import ntu.asu.rduboveckij.api.similarity.SemanticSimilarityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Service
public class SemanticSimilarityServiceImpl extends AbstractSimilarity implements SemanticSimilarityService {
    @Inject
    private DistanceService distanceService;

    @Override
    protected double getScore(String firstName, String secondName) {
        return distanceService.similarity(firstName, secondName);
    }
}