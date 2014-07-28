package ntu.asu.rduboveckij.model.external;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

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
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        Optional<PrimitiveEnum> type = PrimitiveEnum.of(name);
        if(type.isPresent()) return new PrimitiveType(type.get());
        return new ComplexType(name);
    }
}