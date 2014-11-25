package ntu.asu.rduboveckij.service.algorithm.syntatic;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import ntu.asu.rduboveckij.api.settings.AlgorithmSettings;
import ntu.asu.rduboveckij.util.CommonUtils;

import javax.inject.Inject;
import java.util.stream.IntStream;

/**
 * @author andrus.god
 * @since 8/17/2014
 */
public abstract class AbstractLevenshteinDistanceService implements DistanceService {
    @Inject
    protected AlgorithmSettings settings;

    @Override
    public int distance(String first, String second) {
        CommonUtils.requireNotEmpty(first);
        CommonUtils.requireNotEmpty(second);
        return first.length() > second.length() ? getLevenshteinDistance(second, first) : getLevenshteinDistance(first, second);
    }

    private int getLevenshteinDistance(String first, String second) {
        int insertCost = settings.getInsertCost();
        int removeCost = settings.getRemoveCost();

        int n = first.length() + 1, m = second.length() + 1;
        int[][] d = new int[n][m];

        for (int i = 1; i < n; i++) d[i][0] = d[i - 1][0] + removeCost;
        for (int j = 1; j < m; j++) d[0][j] = d[0][j - 1] + insertCost;

        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++)
                d[i][j] = getCost(first, second, d, i, j, insertCost, removeCost)
                        .min()
                        .orElse(0);

        return d[n - 1][m - 1];
    }

    protected abstract IntStream getCost(String first, String second, int[][] d, int i, int j, int insertCost, int removeCost);

    protected int diff(char first, char second) {
        if (first == second) return 0;
        return Character.toLowerCase(first) == Character.toLowerCase(second) ? settings.getReplaceCaseCost() : settings.getReplaceCost();
    }

    @Override
    public double similarity(String first, String second) {
        return 1 - distance(first, second) / (double) (Math.max(first.length(), second.length()) * maxCost());
    }

    protected abstract int maxCost();
}
