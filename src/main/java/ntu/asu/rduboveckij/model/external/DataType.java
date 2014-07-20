package ntu.asu.rduboveckij.model.external;

import java.util.Optional;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public abstract class DataType extends AbstractName {
    public DataType(String name) {
        super(name);
    }

    public boolean isPrimitive() {
        return this instanceof PrimitiveType;
    }

    public boolean isComplex() {
        return this instanceof ComplexType;
    }

    public PrimitiveType asPrimitive() {
        return (PrimitiveType) this;
    }

    public ComplexType asComplex() {
        return (ComplexType) this;
    }

    public static DataType valueOf(String name) {
        Optional<PrimitiveEnum> type = PrimitiveEnum.of(name);
        if(type.isPresent()) return new PrimitiveType(type.get());
        return new ComplexType(name);
    }
}
