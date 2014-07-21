package ntu.asu.rduboveckij.model.external;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Model extends AbstractName<Void> {
    private Set<Element> elements = Sets.newHashSet();

    public Model(String name, Set<Element> elements) {
        super(name);
        this.elements = elements.stream()
                .peek(elem -> elem.setParent(this))
                .collect(Collectors.toSet());
    }

    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    public static class Element extends AbstractName<Model> {
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
    }

    public static class Attribute extends AbstractName<Element> {
        private DataType type;

        public Attribute(String name, DataType type) {
            super(name);
            this.type = type;
        }

        public DataType getType() {
            return type;
        }

        public void setType(DataType type) {
            this.type = type;
        }
    }
}