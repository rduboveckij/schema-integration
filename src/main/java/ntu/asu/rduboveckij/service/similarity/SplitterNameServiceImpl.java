package ntu.asu.rduboveckij.service.similarity;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ntu.asu.rduboveckij.api.algorithm.DictionaryService;
import ntu.asu.rduboveckij.api.algorithm.SplitterTermService;
import ntu.asu.rduboveckij.api.similarity.SplitterNameService;
import ntu.asu.rduboveckij.model.external.AbstractName;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Split;
import ntu.asu.rduboveckij.util.CommonUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Service
public class SplitterNameServiceImpl implements SplitterNameService {
    @Inject
    private SplitterTermService splitterTermService;
    @Inject
    private DictionaryService dictionaryService;

    @Override
    public Split.Element split(Model.Element element) {
        return new Split.Element(element, splitting(element), element.getAttributes()
                .parallelStream()
                .map(attribute -> new Split.Attribute(attribute, splitting(attribute)))
                .collect(Collectors.toSet()));
    }

    private List<String> splitting(AbstractName object) {
        return findBestCombine(splitterTermService.split(object.getName()));
    }

    private List<String> findBestCombine(List<String> names) {
        List<String> combines = Lists.newArrayList(), notCombines = Lists.newArrayList(names);
        for (int level = notCombines.size(); level != 0 && !notCombines.isEmpty(); level--) {
            List<String> currents = generation(notCombines, level);
            combines.addAll(currents);
            notCombines.removeAll(currents.parallelStream()
                    .flatMap(CommonUtils::spaceSplit)
                    .collect(Collectors.toList()));
        }
        return Lists.newArrayList(Iterables.concat(combines, notCombines));
    }

    private List<String> generation(List<String> items, int level) {
        return IntStream.rangeClosed(level, items.size())
                .parallel()
                .mapToObj(i -> items.subList(i - level, i))
                .map(CommonUtils::spaceJoin)
                .filter(dictionaryService::isContain)
                .collect(Collectors.toList());
    }
}