package ntu.asu.rduboveckij.model.external;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Attribute extends AbstractName<Element> {
    private DataType type;

    public Attribute(String name, DataType type) {
        super(name);
        this.type = type;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Attribute attribute = (Attribute) o;

        if (type != null ? !type.equals(attribute.type) : attribute.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}