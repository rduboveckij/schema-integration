package ntu.asu.rduboveckij.model;

import java.util.Objects;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public abstract class AbstractParent<P> {
    private P parent;

    protected AbstractParent() {
    }

    protected AbstractParent(P parent) {
        setParent(parent);
    }

    public P getParent() {
        return parent;
    }

    public void setParent(P parent) {
        this.parent = Objects.requireNonNull(parent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractParent that = (AbstractParent) o;

        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return parent != null ? parent.hashCode() : 0;
    }
}