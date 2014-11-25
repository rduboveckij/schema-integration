package ntu.asu.rduboveckij.model.internal;

import ntu.asu.rduboveckij.model.external.Model;

import java.util.Objects;
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

        public Element(Set<Mapping.Attribute> maps, Set<Model.Attribute> singles, TableIndex<Model.Element> index) {
            super(maps, singles);
            this.index = Objects.requireNonNull(index);
        }

        public TableIndex<Model.Element> getIndex() {
            return index;
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

    public final static class Attribute {
        private final TableIndex<Model.Attribute> index;

        public Attribute(TableIndex<Model.Attribute> index) {
            this.index = Objects.requireNonNull(index);
        }

        public TableIndex<Model.Attribute> getIndex() {
            return index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Attribute element = (Attribute) o;

            return index.equals(element.index);
        }

        @Override
        public int hashCode() {
            return 31 * super.hashCode() + index.hashCode();
        }

        public static Attribute of(Result.Attribute attribute) {
            return new Attribute(attribute.getIndex());
        }
    }
}