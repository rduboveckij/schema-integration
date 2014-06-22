package ntu.asu.rduboveckij.model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class PrimitiveType extends DataType {
    private Set<PrimitiveType> castDataType;

    public PrimitiveType(String name, Set<PrimitiveType> castDataType) {
        super(name);
        this.castDataType = castDataType;
    }

    public Set<PrimitiveType> getCastDataType() {
        return castDataType;
    }

    public void setCastDataType(Set<PrimitiveType> castDataType) {
        this.castDataType = castDataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrimitiveType that = (PrimitiveType) o;

        if (castDataType != null ? !castDataType.equals(that.castDataType) : that.castDataType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (castDataType != null ? castDataType.hashCode() : 0);
        return result;
    }
}
