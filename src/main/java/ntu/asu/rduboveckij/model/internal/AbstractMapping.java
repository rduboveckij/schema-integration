package ntu.asu.rduboveckij.model.internal;

import ntu.asu.rduboveckij.model.external.AbstractModelItem;

import java.util.Objects;
import java.util.Set;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public abstract class AbstractMapping<D, T extends AbstractModelItem> {
    private final Set<D> joined;
    private final Set<T> transferred;

    protected AbstractMapping(Set<D> joined, Set<T> transferred) {
        this.joined = Objects.requireNonNull(joined);
        this.transferred = Objects.requireNonNull(transferred);
    }

    public Set<D> getJoined() {
        return joined;
    }

    public Set<T> getTransferred() {
        return transferred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractMapping that = (AbstractMapping) o;

        return joined.equals(that.joined) && transferred.equals(that.transferred);
    }

    @Override
    public int hashCode() {
        return 31 * joined.hashCode() + transferred.hashCode();
    }
}