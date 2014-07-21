package ntu.asu.rduboveckij.service.settings;

import com.google.common.base.Preconditions;
import ntu.asu.rduboveckij.api.settings.AlgorithmSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Component
public class AlgorithmSettingsImpl implements AlgorithmSettings {
    @Value("#{environment['distance.levenshtein.replaceCost']}")
    private int replaceCost;
    @Value("#{environment['distance.levenshtein.replaceCaseCost']}")
    private int replaceCaseCost;
    @Value("#{environment['distance.levenshtein.insertCost']}")
    private int insertCost;
    @Value("#{environment['distance.levenshtein.removeCost']}")
    private int removeCost;

    @Value("#{environment['dictionary.wordnet.depth']}")
    private int wordNetDepth;

    @PostConstruct
    public void init() {
        Preconditions.checkArgument(replaceCost > 0);
        Preconditions.checkArgument(replaceCaseCost > 0);
        Preconditions.checkArgument(insertCost > 0);
        Preconditions.checkArgument(removeCost > 0);
        Preconditions.checkArgument(wordNetDepth >= 0);
    }

    @Override
    public int getReplaceCost() {
        return replaceCost;
    }

    public void setReplaceCost(int replaceCost) {
        this.replaceCost = replaceCost;
    }

    @Override
    public int getReplaceCaseCost() {
        return replaceCaseCost;
    }

    public void setReplaceCaseCost(int replaceCaseCost) {
        this.replaceCaseCost = replaceCaseCost;
    }

    @Override
    public int getInsertCost() {
        return insertCost;
    }

    public void setInsertCost(int insertCost) {
        this.insertCost = insertCost;
    }

    @Override
    public int getRemoveCost() {
        return removeCost;
    }

    public void setRemoveCost(int removeCost) {
        this.removeCost = removeCost;
    }

    @Override
    public int getWordNetDepth() {
        return wordNetDepth;
    }

    public void setWordNetDepth(int wordNetDepth) {
        this.wordNetDepth = wordNetDepth;
    }
}