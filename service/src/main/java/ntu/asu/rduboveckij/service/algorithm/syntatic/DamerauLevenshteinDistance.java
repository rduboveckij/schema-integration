package ntu.asu.rduboveckij.service.algorithm.syntatic;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * @author andrus.god
 * @since 8/17/2014
 */
@Service(SyntacticAlgorithm.DAMERAU_LEVENSHTEIN)
public class DamerauLevenshteinDistance extends AbstractLevenshteinDistanceService {
    @Override
    protected IntStream getCost(String first, String second, int[][] d, int i, int j, int insertCost, int removeCost) {
        int remove = d[i - 1][j] + removeCost;
        int add = d[i][j - 1] + insertCost;

        char firstCurrent = first.charAt(i - 1), secondCurrent = second.charAt(j - 1);
        int change = d[i - 1][j - 1] + diff(firstCurrent, secondCurrent);

        if (i > 1 && j > 1 && firstCurrent == second.charAt(j - 2) && first.charAt(i - 2) == secondCurrent)
            return IntStream.of(remove, add, change, d[i - 2][j - 2] + settings.getTransformCost());
        return IntStream.of(remove, add, change);
    }

    @Override
    protected int maxCost() {
        return IntStream.of(settings.getReplaceCost(), settings.getReplaceCaseCost(),
                settings.getInsertCost(), settings.getRemoveCost(), settings.getTransformCost())
                .max()
                .orElse(0);
    }
}