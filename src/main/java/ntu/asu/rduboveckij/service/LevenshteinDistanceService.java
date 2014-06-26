package ntu.asu.rduboveckij.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import ntu.asu.rduboveckij.api.DistanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
@Service
public class LevenshteinDistanceService implements DistanceService {
    @Value("#{environment['distance.levenshtein.replaceCost']}")
    public int replaceCost;
    @Value("#{environment['distance.levenshtein.replaceCaseCost']}")
    public int replaceCaseCost;
    @Value("#{environment['distance.levenshtein.insertCost']}")
    public int insertCost;
    @Value("#{environment['distance.levenshtein.removeCost']}")
    public int removeCost;

    @Override
    public int distance(String first, String second) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(first) && !Strings.isNullOrEmpty(second));
        return first.length() > second.length() ? getLevenshteinDistance(second, first) : getLevenshteinDistance(first, second);
    }

    private int getLevenshteinDistance(String first, String second) {
        int n = first.length() + 1, m = second.length() + 1;
        int[][] d = new int[m][n];

        for (int j = 1; j < n; j++) d[0][j] = d[0][j - 1] + insertCost;
        for (int i = 1; i < m; i++) d[i][0] = d[i - 1][0] + removeCost;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                int remove = d[i - 1][j] + removeCost;
                int add = d[i][j - 1] + insertCost;
                int change = d[i - 1][j - 1] + diff(first.charAt(j - 1), second.charAt(i - 1));
                d[i][j] = IntStream.of(remove, add, change).min().getAsInt();
            }

        return d[m - 1][n - 1];
    }

    private int diff(char first, char second) {
        return first == second ? 0 : Character.toLowerCase(first) == Character.toLowerCase(second) ? replaceCaseCost : replaceCost;
    }
}