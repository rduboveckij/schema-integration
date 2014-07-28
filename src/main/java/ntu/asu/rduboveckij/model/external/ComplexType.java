package ntu.asu.rduboveckij.model.external;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class ComplexType extends DataType {
    public static final String ANY_TYPE = "anyType";

    private Model.Element element;

    public ComplexType(String name) {
        super(name);
    }

    public Model.Element getElement() {
        return element;
    }

    public void setElement(Model.Element element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComplexType that = (ComplexType) o;

        if (element != null ? !element.equals(that.element) : that.element != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (element != null ? element.hashCode() : 0);
        return result;
    }
}