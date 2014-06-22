package ntu.asu.rduboveckij.service;

import com.google.common.base.Preconditions;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.parser.XSOMParser;
import ntu.asu.rduboveckij.api.ParserService;
import ntu.asu.rduboveckij.model.Model;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
@Service
public class SchemaParserService implements ParserService {
    @Override
    public Model parse(String uri) throws SAXException {
        Preconditions.checkArgument(new File(uri).exists());
        XSOMParser parser = new XSOMParser();
        parser.parse(uri);
        XSSchema schema = parser.getResult().getSchema(1);
        Collection<XSComplexType> values = schema.getComplexTypes().values();
        return null;
    }

    /*private static Element parseElement(XSComplexType type) {
        XSTerm term = type.getContentType().asParticle().getTerm();
        return new Element(type.getName(), parseAttribute(term).collect(Collectors.toList()));
    }

    private static Stream<Attribute> parseAttribute(XSTerm term) {
        Stream<Attribute> attributes = Stream.empty();
        if (term.isModelGroup()) {
            attributes = Arrays.stream(term.asModelGroup().getChildren())
                    .map(XSParticle::getTerm)
                    .flatMap(SchemaParser::parseAttribute);
        } else if (term.isElementDecl()) {
            attributes = Lists.newArrayList(parseAttribute(term.asElementDecl())).stream();
        }
        return attributes;
    }

    private static Attribute parseAttribute(XSElementDecl decl) {
        return new Attribute(decl.getName(), decl.getType().getName());
    }*/
}
