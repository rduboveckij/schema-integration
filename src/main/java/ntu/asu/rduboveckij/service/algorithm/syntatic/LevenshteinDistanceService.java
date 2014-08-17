package ntu.asu.rduboveckij.service.algorithm.syntatic;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
@Service(SyntacticAlgorithm.LEVENSHTEIN)
public class LevenshteinDistanceService extends AbstractLevenshteinDistanceService {
    protected IntStream getCost(String first, String second, int[][] d, int i, int j, int insertCost, int removeCost) {
        int remove = d[i - 1][j] + removeCost;
        int add = d[i][j - 1] + insertCost;
        int change = d[i - 1][j - 1] + diff(first.charAt(i - 1), second.charAt(j - 1));
        return IntStream.of(remove, add, change);
    }

    @Override
    protected int maxCost() {
        return IntStream.of(settings.getReplaceCost(), settings.getReplaceCaseCost(),
                settings.getInsertCost(), settings.getRemoveCost())
                .max()
                .getAsInt();
    }
}