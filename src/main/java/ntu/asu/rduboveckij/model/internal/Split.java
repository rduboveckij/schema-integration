package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.AbstractParent;
import ntu.asu.rduboveckij.model.external.Model;

import java.util.List;
import java.util.Set;

/**
 * @author andrus.god
 * @since 7/18/2014
 */
public abstract class Split<P> extends AbstractParent<P> {
    private List<String> names = Lists.newArrayList();

    protected Split(P parent, List<String> names) {
        super(parent);
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Split that = (Split) o;

        if (names != null ? !names.equals(that.names) : that.names != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (names != null ? names.hashCode() : 0);
        return result;
    }

    public static class Element extends Split<Model.Element> {
        private Set<Attribute> attributes = Sets.newHashSet();

        public Element(Model.Element parent, List<String> names, Set<Attribute> attributes) {
            super(parent, names);
            this.attributes = attributes;
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(Set<Attribute> attributes) {
            this.attributes = attributes;
        }
    }

    public static class Attribute extends Split<Model.Attribute> {
        public Attribute(Model.Attribute parent, List<String> names) {
            super(parent, names);
        }
    }
}