package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;

import java.util.Objects;
import java.util.Set;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public abstract class AbstractMapping<D, T extends AbstractModelItem> {
    private Set<D> maps = Sets.newHashSet();
    private Set<T> singles = Sets.newHashSet();

    protected AbstractMapping(Set<D> maps, Set<T> singles) {
        this.maps = Objects.requireNonNull(maps);
        this.singles = Objects.requireNonNull(singles);
    }

    public Set<D> getMaps() {
        return maps;
    }

    public void setMaps(Set<D> maps) {
        this.maps = maps;
    }

    public Set<T> getSingles() {
        return singles;
    }

    public void setSingles(Set<T> singles) {
        this.singles = singles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractMapping that = (AbstractMapping) o;

        if (maps != null ? !maps.equals(that.maps) : that.maps != null) return false;
        if (singles != null ? !singles.equals(that.singles) : that.singles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maps != null ? maps.hashCode() : 0;
        result = 31 * result + (singles != null ? singles.hashCode() : 0);
        return result;
    }
}