package ntu.asu.rduboveckij.model.internal;

import java.util.Objects;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public class TableIndex<T> {
    private T source;
    private T target;

    protected TableIndex(T source, T target) {
        this.source = Objects.requireNonNull(source);
        this.target = Objects.requireNonNull(target);
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableIndex that = (TableIndex) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }
}