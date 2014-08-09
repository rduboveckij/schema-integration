package ntu.asu.rduboveckij.model.internal;

import ntu.asu.rduboveckij.model.external.Model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public class Mapping extends AbstractMapping<Mapping.Element, Model.Element> {
    public Mapping(Set<Mapping.Element> joined, Set<Model.Element> transferred) {
        super(joined, transferred);
    }

    public final static class Element extends AbstractMapping<Mapping.Attribute, Model.Attribute> {
        private final TableIndex<Model.Element> index;

        public Element(Set<Mapping.Attribute> maps, Set<Model.Attribute> singles,
                       Model.Element source, Model.Element target) {
            super(maps, singles);
            index = new TableIndex<>(source, target);
        }

        public Model.Element getSource() {
            return index.getSource();
        }

        public Model.Element getTarget() {
            return index.getTarget();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Element element = (Element) o;

            return index.equals(element.index);
        }

        @Override
        public int hashCode() {
            return 31 * super.hashCode() + index.hashCode();
        }
    }

    public final static class Attribute extends TableIndex<Model.Attribute> {
        public Attribute(Model.Attribute source, Model.Attribute target) {
            super(source, target);
        }
    }
}