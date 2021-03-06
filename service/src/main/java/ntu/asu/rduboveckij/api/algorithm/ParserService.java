package ntu.asu.rduboveckij.api.algorithm;

import ntu.asu.rduboveckij.model.external.Model;
import org.xml.sax.SAXException;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
public interface ParserService {
    Model parse(String uri) throws SAXException;
}