package ntu.asu.rduboveckij.model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Element extends AbstractName {
    private Set<Attribute> attributes;

    public Element(String name, Set<Attribute> attributes) {
        super(name);
        this.attributes = attributes;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Element element = (Element) o;

        if (attributes != null ? !attributes.equals(element.attributes) : element.attributes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}
