package ntu.asu.rduboveckij.model;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public abstract class DataType extends AbstractName {
    public DataType(String name) {
        super(name);
    }

    private boolean isSimple() {
        return this instanceof PrimitiveType;
    }

    private boolean isCustom() {
        return this instanceof ComplexType;
    }

    public PrimitiveType asSimple() {
        return (PrimitiveType) this;
    }

    public ComplexType asCustom() {
        return (ComplexType) this;
    }
}
