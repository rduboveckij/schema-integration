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

    protected Result(T source, T target, double score) {
        this.index = new TableIndex<>(source, target);
        this.score = score;
    }

    public T getSource() {
        return index.getSource();
    }

    public T getTarget() {
        return index.getTarget();
    }

    public TableIndex<T> getIndex() {
        return index;
    }

    public double getScore() {
        return score;
    }

    public final static class Element extends Result<Model.Element> {
        private final Set<Attribute> attributes;

        public Element(Model.Element source, Model.Element target, double score, Set<Attribute> attributes) {
            super(source, target, score);
            this.attributes = Objects.requireNonNull(attributes);
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }
    }

    public final static class Attribute extends Result<Model.Attribute> {
        public Attribute(Model.Attribute source, Model.Attribute target, double score) {
            super(source, target, score);
        }
    }

    public static Mapping.Element toMapping(Element result) {
        return new Mapping.Element(Sets.newHashSet(), Sets.newHashSet(), result.getSource(), result.getTarget());
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