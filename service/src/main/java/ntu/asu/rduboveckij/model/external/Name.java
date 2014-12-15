package ntu.asu.rduboveckij.model.external;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public class Name implements Serializable {
    private final String name;

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

        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}