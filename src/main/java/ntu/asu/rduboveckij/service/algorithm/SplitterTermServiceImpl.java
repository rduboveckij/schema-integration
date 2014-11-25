package ntu.asu.rduboveckij.service.algorithm;

import com.google.common.base.Splitter;
import ntu.asu.rduboveckij.api.algorithm.SplitterTermService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
@Service
public class SplitterTermServiceImpl implements SplitterTermService {
    private static final String PATTERN = Stream.of("\\p{Punct}", "\\p{Blank}", "\\p{Cntrl}", "\\p{Space}", "(?<=\\p{Lower})(?=\\p{Upper})")
            .collect(Collectors.joining("|", "(", ")"));

    @Override
    public List<String> split(String term) {
        return Splitter.onPattern(PATTERN)
                .omitEmptyStrings()
                .trimResults()
                .splitToList(term)
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}