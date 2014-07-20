package ntu.asu.rduboveckij.service;

import com.google.common.base.Splitter;
import ntu.asu.rduboveckij.api.SplitterTermService;
import ntu.asu.rduboveckij.model.external.Element;
import ntu.asu.rduboveckij.model.internal.AttributeSplit;
import ntu.asu.rduboveckij.model.internal.ElementSplit;
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
    private static final String PATTERN = Stream.of("\\p{Punct}", "\\p{Blank}", "\\p{Cntrl}", "\\p{Space}", "(?<=[Lower])(?=\\p{Upper})")
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