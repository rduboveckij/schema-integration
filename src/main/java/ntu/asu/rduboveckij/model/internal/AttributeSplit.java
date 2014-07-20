package ntu.asu.rduboveckij.model.internal;

import ntu.asu.rduboveckij.model.external.Attribute;

import java.util.List;

/**
 * @author andrus.god
 * @since 7/18/2014
 */
public class AttributeSplit extends AbstractNameSplit<Attribute> {
    public AttributeSplit(Attribute parent, List<String> names) {
        super(parent, names);
    }
}
