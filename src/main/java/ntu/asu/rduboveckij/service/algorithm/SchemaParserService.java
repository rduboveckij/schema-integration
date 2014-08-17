package ntu.asu.rduboveckij.service.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.xml.xsom.*;
import com.sun.xml.xsom.parser.XSOMParser;
import ntu.asu.rduboveckij.api.algorithm.ParserService;
import ntu.asu.rduboveckij.model.external.ComplexType;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
@Service
public class SchemaParserService implements ParserService {


    @Override
    public Model parse(String uri) throws SAXException {
        if (!new File(uri).exists()) throw new SAXException("File is not exists by uri - " + uri);

        XSOMParser parser = new XSOMParser();
        parser.parse(uri);
        XSSchema schema = parser.getResult().getSchema(1);

        Map<String, Model.Element> mapElements = schema.getComplexTypes().values()
                .parallelStream()
                .filter(type -> !type.isAbstract())
                .map(SchemaParserService::parseElement)
                .collect(Collectors.toConcurrentMap(Model.Element::getName, Function.<Model.Element>identity()));

        Set<Model.Element> elements = Sets.newHashSet(mapElements.values());
        elements.parallelStream()
                .flatMap(elem -> elem.getAttributes().stream())
                .map(Model.Attribute::getType)
                .filter(DataType::isComplex)
                .map(DataType::asComplex)
                .map(type -> type.setElement(mapElements.get(type.getName())));
        return new Model(uri, elements);
    }

    private static Model.Element parseElement(XSComplexType type) {
        XSTerm term = type.getContentType().asParticle().getTerm();
        XSType base = type.getBaseType();
        // todo: warning use stack
        Model.Element extend = !ComplexType.ANY_TYPE.equals(base.getName()) ? parseElement(base.asComplexType()) : null;
        return new Model.Element(CommonUtils.requireNotEmpty(type.getName()), parseAttribute(term).collect(Collectors.toSet()), extend);
    }

    private static Stream<Model.Attribute> parseAttribute(XSTerm term) {
        if (term.isModelGroup())
            return Arrays.stream(term.asModelGroup().getChildren())
                    .map(XSParticle::getTerm)
                    .flatMap(SchemaParserService::parseAttribute);
        else if (term.isElementDecl())
            return Lists.newArrayList(parseAttribute(term.asElementDecl())).stream();
        return Stream.empty();
    }

    private static Model.Attribute parseAttribute(XSElementDecl decl) {
        return new Model.Attribute(CommonUtils.requireNotEmpty(decl.getName()),
                DataType.valueOf(decl.getType().getName()));
    }
}