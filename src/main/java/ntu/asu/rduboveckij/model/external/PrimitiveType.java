package ntu.asu.rduboveckij.model.external;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public final class PrimitiveType extends DataType {
    private PrimitiveEnum primitiveEnum;

    public PrimitiveType(PrimitiveEnum primitiveEnum) {
        super(primitiveEnum.getName());
        this.primitiveEnum = primitiveEnum;
    }

    public PrimitiveEnum getPrimitiveEnum() {
        return primitiveEnum;
    }

    public void setPrimitiveEnum(PrimitiveEnum primitiveEnum) {
        this.primitiveEnum = primitiveEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrimitiveType that = (PrimitiveType) o;

        if (primitiveEnum != that.primitiveEnum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (primitiveEnum != null ? primitiveEnum.hashCode() : 0);
        return result;
    }
}