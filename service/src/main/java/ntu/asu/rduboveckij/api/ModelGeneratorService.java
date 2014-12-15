package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.model.ModelGeneratorException;
import ntu.asu.rduboveckij.model.internal.Mapping;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author andrus.god
 * @since 14.12.2014.
 */
public interface ModelGeneratorService {
    void generate(Mapping mapping) throws ParserConfigurationException, TransformerException, ModelGeneratorException;
}