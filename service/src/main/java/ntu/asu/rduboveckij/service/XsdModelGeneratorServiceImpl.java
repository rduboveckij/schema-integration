package ntu.asu.rduboveckij.service;

import ntu.asu.rduboveckij.api.ModelGeneratorService;
import ntu.asu.rduboveckij.model.ModelGeneratorException;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Mapping;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import ntu.asu.rduboveckij.util.Pair;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 14.12.2014.
 */
@Service
public class XsdModelGeneratorServiceImpl implements ModelGeneratorService {
    private static final String FILE_NAME = "result" + Instant.now().toEpochMilli() + ".xsd";
    private static final String XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
    private static final String XSD_PREFIX = "xs:";
    private static final String XSD_SCHEMA_NAME = XSD_PREFIX + "schema";
    private static final String XSD_COMPLEX_TYPE = XSD_PREFIX + "complexType";
    private static final String XSD_SEQUENCE = XSD_PREFIX + "sequence";
    private static final String XSD_ELEMENT = XSD_PREFIX + "element";

    @Override
    public void generate(Mapping mapping) throws ModelGeneratorException {
        Document doc = createDocument();

        Element schema = doc.createElementNS(XSD_NAMESPACE, XSD_SCHEMA_NAME);
        schema.setAttribute("version", "1.0");
        doc.appendChild(schema);

        mapping.getTransferred()
                .parallelStream()
                .map(elem -> createComplexType(doc, elem.getName(), transferredAttributes(elem.getAttributes(), new HashSet<>())))
                .forEach(schema::appendChild);

        Set<Mapping.Element> joined = mapping.getJoined();
        joined.parallelStream()
                .map(elem -> {
                    Set<Pair<String, String>> attributes = new HashSet<>();
                    attributes.addAll(joinedAttributes(elem.getJoined()));
                    attributes.addAll(transferredAttributes(elem.getTransferred(), joined));
                    return createComplexType(doc, nameStrategy(elem.getIndex()), attributes);
                })
                .forEach(schema::appendChild);

        generateToFile(doc);
    }

    private void generateToFile(Document doc) throws ModelGeneratorException {
        try {
            TransformerFactory.newInstance()
                    .newTransformer()
                    .transform(new DOMSource(doc), new StreamResult(Paths.get(FILE_NAME).toFile()));
        } catch (TransformerException e) {
            throw new ModelGeneratorException(e);
        }
    }

    private Document createDocument() throws ModelGeneratorException {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            doc.setXmlVersion("1.0");
            doc.setXmlStandalone(true);
            return doc;
        } catch (ParserConfigurationException e) {
            throw new ModelGeneratorException(e);
        }
    }

    private Element createComplexType(Document doc, String name, Set<Pair<String, String>> attributes) {
        Element type = doc.createElementNS(XSD_NAMESPACE, XSD_COMPLEX_TYPE);
        type.setAttribute("name", name);
        Element sequence = doc.createElementNS(XSD_NAMESPACE, XSD_SEQUENCE);
        attributes.forEach(attr -> sequence.appendChild(createElement(doc, attr)));
        type.appendChild(sequence);
        return type;
    }

    private Element createElement(Document doc, Pair<String, String> attribute) {
        Element element = doc.createElementNS(XSD_NAMESPACE, XSD_ELEMENT);
        element.setAttribute("name", attribute.getKey());
        element.setAttribute("type", attribute.getValue());
        return element;
    }

    private <T extends AbstractModelItem> String nameStrategy(TableIndex<T> index) {
        return index.getSource().getName();
    }

    private Set<Pair<String, String>> transferredAttributes(Set<Model.Attribute> attributes, Set<Mapping.Element> mapping) {
        return attributes.parallelStream()
                .map(attr -> {
                    DataType type = attr.getType();
                    if (type.isComplex()) {
                        Optional<Mapping.Element> element = mapping.stream()
                                .filter(mp -> Objects.equals(mp.getIndex().getTarget().getName(), type.asComplex().getName()))
                                .findAny();
                        if (element.isPresent()) {
                            return Pair.of(attr.getName(), element.get().getIndex().getSource().getName());
                        }
                    }
                    return Pair.of(attr.getName(), typeStrategy(attr.getType()));
                })
                .collect(Collectors.toSet());
    }

    private Set<Pair<String, String>> joinedAttributes(Set<Mapping.Attribute> attributes) {
        return attributes.parallelStream()
                .map(attr -> Pair.of(nameStrategy(attr.getIndex()), typeStrategy(attr.getIndex().getSource().getType())))
                .collect(Collectors.toSet());
    }

    private String typeStrategy(DataType type) {
        return (type.isPrimitive() ? XSD_PREFIX : "") + type.getName();
    }
}