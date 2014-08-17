package ntu.asu.rduboveckij.service.algorithm.syntatic;

import com.google.common.base.Preconditions;
import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import ntu.asu.rduboveckij.api.settings.AlgorithmSettings;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author andrus.god
 * @since 8/15/2014
 */
@Component
public class DistanceStrategy {
    @Inject
    private Map<String, DistanceService> syntacticAlgorithms;
    @Inject
    private AlgorithmSettings algorithmSettings;

    public double similarity(String first, String second) {
        return getAlgorithm().similarity(first, second);
    }

    private DistanceService getAlgorithm() {
        String distanceStrategy = algorithmSettings.getDistanceStrategy();
        DistanceService distanceService = syntacticAlgorithms.get(distanceStrategy);
        Preconditions.checkArgument(distanceService != null, "Syntactic algorithm bean " + distanceStrategy + " isn't found in the context " + syntacticAlgorithms.keySet());
        return distanceService;
    }
}