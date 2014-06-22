package ntu.asu.rduboveckij.service;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
public class LevenshteinDistanceService {
    /*public final int replaceCost;
    public final int replaceCaseCost;
    public final int insertCost;
    public final int removeCost;

    public LevenshteinMetric() {
        replaceCost = 1;
        replaceCaseCost = 1;
        insertCost = 1;
        removeCost = 1;
    }

    public LevenshteinMetric(int replaceCost, int replaceCaseCost, int insertCost, int removeCost) {
        this.replaceCost = replaceCost;
        this.replaceCaseCost = replaceCaseCost;
        this.insertCost = insertCost;
        this.removeCost = removeCost;
    }

    @Override
    public int distance(String first, String second) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(first) && !Strings.isNullOrEmpty(second));
        return first.length() > second.length() ? getLevenshteinDistance(second, first, this) : getLevenshteinDistance(first, second, this);
    }

    private static int getLevenshteinDistance(String first, String second, LevenshteinMetric metric) {
        int n = first.length() + 1, m = second.length() + 1;
        int[][] d = new int[m][n];

        for (int j = 1; j < n; j++) d[0][j] = d[0][j - 1] + metric.insertCost;
        for (int i = 1; i < m; i++) d[i][0] = d[i - 1][0] + metric.removeCost;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                int remove = d[i - 1][j] + metric.removeCost;
                int add = d[i][j - 1] + metric.insertCost;
                int change = d[i - 1][j - 1] + diff(first.charAt(j - 1), second.charAt(i - 1), metric);
                d[i][j] = min(remove, add, change);
            }

        return d[m - 1][n - 1];
    }

    private static int diff(char first, char second, LevenshteinMetric metric) {
        return first == second ? 0 : Character.toLowerCase(first) == Character.toLowerCase(second) ? metric.replaceCaseCost : metric.replaceCost;
    }

    private static int min(int... args) {
        return Arrays.stream(args).min().getAsInt();
    }*/
}
