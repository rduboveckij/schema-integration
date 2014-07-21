package ntu.asu.rduboveckij.api.algorithm;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
public interface DistanceService {
    int distance(String first, String second);

    double similarity(String first, String second);
}
