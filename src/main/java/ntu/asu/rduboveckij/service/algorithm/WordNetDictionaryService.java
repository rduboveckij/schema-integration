package ntu.asu.rduboveckij.service.algorithm;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;
import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
import ntu.asu.rduboveckij.api.settings.AlgorithmSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Optional;

import static net.sf.extjwnl.data.relationship.RelationshipFinder.findRelationships;

/**
 * @author andrus.god
 * @since 6/29/2014
 */
@Service
public class WordNetDictionaryService implements DictionaryService {
    @Inject
    private AlgorithmSettings settings;

    private Dictionary dictionary;

    @PostConstruct
    public void init() throws JWNLException {
        dictionary = Dictionary.getDefaultResourceInstance();
    }

    @Override
    public boolean isSynonym(String first, String second) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(first) && !Strings.isNullOrEmpty(second));
        return first.equals(second) || POS.getAllPOS().stream()
                .anyMatch(pos -> isSynonym(first, second, pos));
    }

    private boolean isSynonym(String first, String second, POS pos) {
        Optional<IndexWord> start = getIndexWord(pos, first);
        Optional<IndexWord> end = getIndexWord(pos, second);
        return start.isPresent() && end.isPresent() &&
                !findRelations(start.get(), end.get()).isEmpty();
    }

    private Optional<IndexWord> getIndexWord(POS pos, String term) {
        try {
            return Optional.ofNullable(dictionary.getIndexWord(pos, term));
        } catch (JWNLException e) {
            throw new RuntimeException(e);
        }
    }

    private RelationshipList findRelations(IndexWord start, IndexWord end) {
        try {
            Synset sourceSynset = start.getSenses().get(0);
            Synset targetSynset = end.getSenses().get(0);
            PointerType type = PointerType.SIMILAR_TO;
            int wordNetDepth = settings.getWordNetDepth();
            return wordNetDepth == 0 ? findRelationships(sourceSynset, targetSynset, type) :
                    findRelationships(sourceSynset, targetSynset, type, wordNetDepth);
        } catch (CloneNotSupportedException | JWNLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isContain(String term) {
        return POS.getAllPOS().stream()
                .map(pos -> getIndexWord(pos, term))
                .anyMatch(Optional::isPresent);
    }
}