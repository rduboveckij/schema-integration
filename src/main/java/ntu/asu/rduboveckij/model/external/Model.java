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
}