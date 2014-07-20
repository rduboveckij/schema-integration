package ntu.asu.rduboveckij.model.external;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.AbstractParent;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Element extends AbstractName<Model> {
    private Set<Attribute> attributes = Sets.newHashSet();
    private Element extend;

    public Element(String name, Set<Attribute> attributes) {
        super(name);
        this.attributes = attributes.stream()
                .peek(attr -> attr.setParent(this))
                .collect(Collectors.toSet());
    }

    public Element getExtend() {
        return extend;
    }

    public void setExtend(Element extend) {
        this.extend = extend;
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
        if (extend != null ? !extend.equals(element.extend) : element.extend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (extend != null ? extend.hashCode() : 0);
        return result;
    }
}
