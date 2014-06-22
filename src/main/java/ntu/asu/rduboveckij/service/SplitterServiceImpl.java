package ntu.asu.rduboveckij.service;

import com.google.common.base.Splitter;
import ntu.asu.rduboveckij.api.SplitterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author andrus.god
* @since 16.06.2014
 */
@Service
public class SplitterServiceImpl implements SplitterService {
    @Override
    public List<String> splitter(String term) {
        String pattern = Stream.of("\\p{Punct}", "\\p{Blank}", "\\p{Cntrl}", "\\p{Space}", "(?<=[Lower])(?=\\p{Upper})")
                .collect(Collectors.joining("|", "(", ")"));
        return Splitter.onPattern(pattern)
                .omitEmptyStrings()
                .trimResults()
                .splitToList(term)
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}