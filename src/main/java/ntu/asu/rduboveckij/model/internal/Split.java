package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractParent;
import ntu.asu.rduboveckij.model.external.Model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author andrus.god
 * @since 7/18/2014
 */
public abstract class Split<P> extends AbstractParent<P> {
    private final List<String> names;

    protected Split(P parent, List<String> names) {
        super(parent);
        this.names = Objects.requireNonNull(names);
    }

    public List<String> getNames() {
        return names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Split that = (Split) o;

        return names.equals(that.names);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + names.hashCode();
    }

    public final static class Element extends Split<Model.Element> {
        private final Set<Attribute> attributes;

        public Element(Model.Element parent, List<String> names, Set<Attribute> attributes) {
            super(parent, names);
            this.attributes = Objects.requireNonNull(attributes);
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }
    }

    public final static class Attribute extends Split<Model.Attribute> {
        public Attribute(Model.Attribute parent, List<String> names) {
            super(parent, names);
        }
    }
}