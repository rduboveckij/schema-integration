package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Split;

import java.util.Set;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
public interface SplitterNameService {
    Set<Split.Element> split(Model model);

    Split.Element split(Model.Element element);
}