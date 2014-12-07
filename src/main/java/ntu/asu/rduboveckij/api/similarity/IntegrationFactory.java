package ntu.asu.rduboveckij.api.similarity;

import ntu.asu.rduboveckij.model.Report;
import ntu.asu.rduboveckij.model.internal.Mapping;
import org.xml.sax.SAXException;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
public interface IntegrationFactory {
    Service parse(String sourceUri, String targetUri) throws SAXException;

    interface Service {
        Mapping integration();

        double metricCompleteness();
    }
}