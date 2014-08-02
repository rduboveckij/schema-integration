package ntu.asu.rduboveckij.model.external;

import ntu.asu.rduboveckij.model.AbstractParent;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public abstract class AbstractModelItem<P> extends AbstractParent<P> {
    private Name name;

    protected AbstractModelItem(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractModelItem that = (AbstractModelItem) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}