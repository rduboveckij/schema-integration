package ntu.asu.rduboveckij.util;

/**
 * @author andrus.god
 * @since 7/23/2014
 */
public class Pair<K, V> extends javafx.util.Pair<K, V> {
    private Pair(K key, V value) {
        super(key, value);
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}