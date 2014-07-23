package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractName;
import ntu.asu.rduboveckij.model.external.Model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public abstract class Result<T extends AbstractName> {
    private T source;
    private T target;
    private double score;

    protected Result(T source, T target, double score) {
        this.source = source;
        this.target = target;
        this.score = score;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (Double.compare(result.score, score) != 0) return false;
        if (source != null ? !source.equals(result.source) : result.source != null) return false;
        if (target != null ? !target.equals(result.target) : result.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class Element extends Result<Model.Element> {
        private Set<Attribute> attributes = Sets.newHashSet();

        public Element(Model.Element source, Model.Element target, double score, Set<Attribute> attributes) {
            super(source, target, score);
            this.attributes = attributes;
        }

        public Set<Attribute> getAttributes() {
            return attributes;
        }

        public void setAttributes(Set<Attribute> attributes) {
            this.attributes = attributes;
        }
    }

    public static class Attribute extends Result<Model.Attribute> {
        public Attribute(Model.Attribute source, Model.Attribute target, double score) {
            super(source, target, score);
        }
    }
}