package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Lists;
import ntu.asu.rduboveckij.model.AbstractParent;

import java.util.List;

/**
 * @author andrus.god
 * @since 7/18/2014
 */
public abstract class AbstractNameSplit<P> extends AbstractParent<P> {
    private List<String> names = Lists.newArrayList();

    protected AbstractNameSplit(P parent, List<String> names) {
        super(parent);
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractNameSplit that = (AbstractNameSplit) o;

        if (names != null ? !names.equals(that.names) : that.names != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (names != null ? names.hashCode() : 0);
        return result;
    }
}