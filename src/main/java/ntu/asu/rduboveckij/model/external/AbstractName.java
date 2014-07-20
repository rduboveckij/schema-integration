package ntu.asu.rduboveckij.model.external;

import ntu.asu.rduboveckij.model.AbstractParent;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public abstract class AbstractName<P> extends AbstractParent<P> {
    private String name;

    protected AbstractName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractName that = (AbstractName) o;

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