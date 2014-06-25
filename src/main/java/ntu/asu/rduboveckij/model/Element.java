package ntu.asu.rduboveckij.model;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Element extends AbstractName {
    private Set<Attribute> attributes;

    public Element(String name, Set<Attribute> attributes) {
        super(name);
        this.attributes = attributes.stream()
                .peek(attr -> attr.setElement(this))
                .collect(Collectors.toSet());
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
}
