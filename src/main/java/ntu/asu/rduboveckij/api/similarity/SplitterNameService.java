package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Split;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface SplitterNameService {
    Split.Element split(Model.Element element);
}