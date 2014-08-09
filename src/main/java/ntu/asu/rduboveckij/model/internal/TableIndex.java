package ntu.asu.rduboveckij.model.internal;

import java.util.Objects;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public class TableIndex<T> {
    private final T source;
    private final T target;

    protected TableIndex(T source, T target) {
        this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
    }

    public T getSource() {
        return source;
    }

    public T getTarget() {
        return target;
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
}