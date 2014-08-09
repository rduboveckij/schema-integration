package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.external.Model;

import java.util.Objects;
import java.util.Set;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public abstract class Result<T extends AbstractModelItem> {
    private final TableIndex<T> index;
    private final double score;

    protected Result(TableIndex<T> index, double score) {
        this.index = Objects.requireNonNull(index);
        this.score = score;
    }

    public TableIndex<T> getIndex() {
        return index;
    }

    public double getScore() {
        return score;
    }

    public final static class Element extends Result<Model.Element> {
        private final Set<Attribute> attributes;

        public Element(TableIndex<Model.Element> index, double score, Set<Attribute> attributes) {
            super(index, score);
            this.attributes = Objects.requireNonNull(attributes);
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }
    }

    public final static class Attribute extends Result<Model.Attribute> {
        public Attribute(TableIndex<Model.Attribute> index, double score) {
            super(index, score);
        }
    }

    public static Mapping.Element toMapping(Element result) {
        return new Mapping.Element(Sets.newHashSet(), Sets.newHashSet(), result.getIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return Double.compare(result.score, score) == 0 && index.equals(result.index);
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(score);
        return 31 * index.hashCode() + (int) (temp ^ (temp >>> 32));
    }
}