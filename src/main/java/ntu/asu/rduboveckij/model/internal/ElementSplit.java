package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.Attribute;
import ntu.asu.rduboveckij.model.external.Element;

import java.util.List;
import java.util.Set;

/**
 * @author andrus.god
 * @since 7/18/2014
 */
public class ElementSplit extends AbstractNameSplit<Element> {
    private Set<AttributeSplit> attributes = Sets.newHashSet();

    public ElementSplit(Element parent, List<String> names, Set<AttributeSplit> attributes) {
        super(parent, names);
        this.attributes = attributes;
    }

    public Set<AttributeSplit> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AttributeSplit> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ElementSplit that = (ElementSplit) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}