package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.algorithm.ParserService;
import ntu.asu.rduboveckij.api.settings.SimilaritySettings;
import ntu.asu.rduboveckij.api.similarity.*;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Mapping;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import ntu.asu.rduboveckij.util.CommonUtils;
import ntu.asu.rduboveckij.util.Pair;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
    private SyntacticSimilarityService syntacticSimilarityService;
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
            Set<Result.Element> results = CommonUtils.eachStream(sourceElements, targetElements, this::integration)
                    .filter(result -> result.getScore() > settings.getThresholdFactor())
                    .collect(toSet());
            Set<Mapping.Element> joined = CommonUtils.similarityFilter(results)
                    .parallelStream()
                    .map(Result::toMapping)
                    .collect(toSet());
            return new Mapping(joined, findTransferred(results));
        }

        private Result.Element integration(Split.Element source, Split.Element target) {
            Result.Element syntactic = syntacticSimilarityService.similarity(source, target);
            Result.Element dictionary = dictionarySimilarityService.similarity(source, target);
            Result.Element dataType = dataTypeSimilarityService.similarity(source, target);

            double syntacticFactor = settings.getSyntacticFactor();
            double dictionaryFactor = settings.getDictionaryFactor();
            double dataTypeFactor = settings.getDataTypeFactor();
            Set<Result.Attribute> attributes = Stream.of(
                    getPairStreamWithFactorAndResult(syntactic, syntacticFactor),
                    getPairStreamWithFactorAndResult(dictionary, dictionaryFactor),
                    getPairStreamWithFactorAndResult(dataType, dataTypeFactor)
            )
                    .flatMap(stream -> stream)
                    .collect(groupingBy(pair -> pair.getValue().getIndex(), mapping(pair -> Pair.of(pair.getKey(), pair.getValue().getScore()), toList())))
                    .entrySet()
                    .parallelStream()
                    .map(entry -> new Result.Attribute(entry.getKey(), CommonUtils.normal(entry.getValue())))
                    .collect(toSet());

            double elementScore = CommonUtils.normal(
                    Pair.of(syntacticFactor, syntactic.getScore()),
                    Pair.of(dictionaryFactor, dictionary.getScore()),
                    Pair.of(dataTypeFactor, dataType.getScore())
            );
            return new Result.Element(TableIndex.of(source, target), elementScore, attributes);
        }

        private Stream<Pair<Double, Result.Attribute>> getPairStreamWithFactorAndResult(Result.Element syntactic, double id) {
            return syntactic.getAttributes().parallelStream()
                    .map(attr -> Pair.of(id, attr));
        }

        private Set<Model.Element> findTransferred(Set<Result.Element> joined) {
            Set<Model.Element> singleMapping = Stream.concat(sourceElements.stream(), targetElements.stream())
                    .parallel()
                    .map(split -> split.getParent())
                    .collect(toSet());

            Set<Model.Element> results = joined.parallelStream()
                    .flatMap(result -> result.getIndex().getAll().stream())
                    .collect(toSet());
            singleMapping.removeAll(results);
            return singleMapping;
        }

        @Override
        public double metricCompleteness() {
            return Stream.concat(sourceElements.stream(), targetElements.stream())
                    .parallel()
                    .mapToDouble(dictionarySimilarityService::metricCompleteness)
                    .average()
                    .getAsDouble();
        }
    }
}