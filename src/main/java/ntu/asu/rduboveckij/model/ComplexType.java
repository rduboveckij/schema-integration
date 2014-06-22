package ntu.asu.rduboveckij.model;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class ComplexType extends DataType {
    private Element element;

    public ComplexType(String name, Element element) {
        super(name);
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
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
