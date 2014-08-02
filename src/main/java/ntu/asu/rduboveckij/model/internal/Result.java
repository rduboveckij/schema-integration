package ntu.asu.rduboveckij.model.internal;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.external.Model;

import java.util.Set;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public abstract class Result<T extends AbstractModelItem> extends TableIndex<T> {
    private double score;

    protected Result(T source, T target, double score) {
        super(source, target);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public final static class Element extends Result<Model.Element> {
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

    public final static class Attribute extends Result<Model.Attribute> {
        public Attribute(Model.Attribute source, Model.Attribute target, double score) {
            super(source, target, score);
        }
    }
}