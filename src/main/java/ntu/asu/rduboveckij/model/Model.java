package ntu.asu.rduboveckij.model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public class Model extends AbstractName {
    private Set<Element> elements;

    public Model(String name, Set<Element> elements) {
        super(name);
        this.elements = elements;
    }

    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Model model = (Model) o;

        if (elements != null ? !elements.equals(model.elements) : model.elements != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        return result;
    }
}
