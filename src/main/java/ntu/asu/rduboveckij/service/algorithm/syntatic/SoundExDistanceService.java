package ntu.asu.rduboveckij.service.algorithm.syntatic;

import ntu.asu.rduboveckij.api.algorithm.DistanceService;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author andrus.god
 * @since 8/15/2014
 */
@Service(SyntacticAlgorithm.SOUND_EX)
public class SoundExDistanceService implements DistanceService {
    private Soundex soundex;

    @PostConstruct
    public void init() {
        soundex = new Soundex();
    }

    @Override
    public int distance(String first, String second) {
        try {
            return soundex.difference(first, second);
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double similarity(String first, String second) {
        return distance(first, second) / 4.0;
    }
}