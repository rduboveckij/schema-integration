package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.ParserService;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.*;
import ntu.asu.rduboveckij.model.internal.Mapping;
import ntu.asu.rduboveckij.model.internal.Split;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Set;

/**
 * @author andrus.god
 * @since 8/2/2014
 */
@Component
public class IntegrationFactoryImpl implements IntegrationFactory {
    @Inject
    private ParserService parserService;
    @Inject
    private SplitterNameService splitterNameService;
    @Inject
    private SemanticSimilarityService semanticSimilarityService;
    @Inject
    private DictionarySimilarityService dictionarySimilarityService;
    @Inject
    private DataTypeSimilarityService dataTypeSimilarityService;
    @Inject
    private SimilaritySettings settings;

    @Override
    public Service parse(String sourceUri, String targetUri) throws SAXException {
        Set<Split.Element> sourceElements = splitterNameService.split(parserService.parse(sourceUri));
        Set<Split.Element> targetElements = splitterNameService.split(parserService.parse(targetUri));
        return new IntegrationServiceImpl(sourceElements, targetElements);
    }

    public class IntegrationServiceImpl implements Service {
        private final Set<Split.Element> sourceElements;
        private final Set<Split.Element> targetElements;

        public IntegrationServiceImpl(Set<Split.Element> sourceElements, Set<Split.Element> targetElements) {
            this.sourceElements = Objects.requireNonNull(sourceElements);
            this.targetElements = Objects.requireNonNull(targetElements);
        }

        @Override
        public Mapping integration() {
            return null;
        }

        @Override
        public double metricCompleteness() {
            return 0;
        }
    }
}