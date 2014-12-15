package ntu.asu.rduboveckij.model.external;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public final class Model extends Name {
    private final Set<Element> elements;

    public Model(String name, Set<Element> elements) {
        super(name);
        this.elements = Objects.requireNonNull(elements).stream()
                .peek(elem -> elem.setParent(this))
                .collect(Collectors.toSet());
    }

    public Set<Element> getElements() {
        return elements;
    }

    public final static class Element extends AbstractModelItem<Model> {
        private final Set<Attribute> attributes;
        private final Optional<Element> extend;

        public Element(String name, Set<Attribute> attributes, Element extend) {
            super(name);
            this.extend = Optional.ofNullable(extend);
            this.attributes = Objects.requireNonNull(attributes).stream()
                    .peek(attr -> attr.setParent(this))
                    .collect(Collectors.toSet());
        }

        public Optional<Element> getExtend() {
            return extend;
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }
    }

    public final static class Attribute extends AbstractModelItem<Element> {
        private final DataType type;

        public Attribute(String name, DataType type) {
            super(name);
            this.type = Objects.requireNonNull(type);
        }

        public DataType getType() {
            return type;
        }
    }
}