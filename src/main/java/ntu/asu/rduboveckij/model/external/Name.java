package ntu.asu.rduboveckij.model.external;

import java.util.Objects;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public class Name {
    protected final String name;

    public Name(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name1 = (Name) o;

        if (name != null ? !name.equals(name1.name) : name1.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}