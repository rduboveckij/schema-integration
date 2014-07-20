package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.model.external.Element;
import ntu.asu.rduboveckij.model.internal.ElementSplit;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface SplitterNameService {
    ElementSplit split(Element element);
}
