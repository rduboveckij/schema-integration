package ntu.asu.rduboveckij.model.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public final class ComplexType extends DataType {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComplexType.class);
    public static final String ANY_TYPE = "anyType";

    private final Model.Element element;

    public ComplexType(String name) {
        this(name, null);
    }

    public ComplexType(String name, Model.Element element) {
        super(name);
        this.element = element;
    }

    public Model.Element getElement() {
        return element;
    }

    public ComplexType setElement(Model.Element element) {
        if (element == null) {
            LOGGER.warn("Data type " + getName() + " is not found!");
            return this;
        }
        return new ComplexType(getName(), element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComplexType that = (ComplexType) o;

        return !(element != null ? !element.equals(that.element) : that.element != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (element != null ? element.hashCode() : 0);
        return result;
    }
}