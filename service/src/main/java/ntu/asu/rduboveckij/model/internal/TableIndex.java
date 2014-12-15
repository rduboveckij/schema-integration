package ntu.asu.rduboveckij.model.internal;

import ntu.asu.rduboveckij.model.external.AbstractParent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public final class TableIndex<T> {
    private final T source;
    private final T target;

    private TableIndex(T source, T target) {
        this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
    }

    public T getSource() {
        return source;
    }

    public T getTarget() {
        return target;
    }

    public List<T> getAll() {
        return Arrays.asList(source, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableIndex that = (TableIndex) o;

        return source.equals(that.source) && target.equals(that.target);
    }

    @Override
    public int hashCode() {
        return 31 * source.hashCode() + target.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "source=" + source +
                ", target=" + target +
                '}';
    }

    public static <T> TableIndex<T> of(T source, T target) {
        return new TableIndex<>(source, target);
    }

    public static <P, T extends AbstractParent<P>> TableIndex<P> of(T source, T target) {
        return new TableIndex<>(source.getParent(), target.getParent());
    }
}