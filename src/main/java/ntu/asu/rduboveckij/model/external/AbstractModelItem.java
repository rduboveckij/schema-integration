package ntu.asu.rduboveckij.model.external;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public abstract class AbstractModelItem<P> extends AbstractParent<P> {
    private final Name name;

    protected AbstractModelItem(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractModelItem that = (AbstractModelItem) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name.hashCode());
        return result;
    }
}