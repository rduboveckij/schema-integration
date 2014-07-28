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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        List<String> combines = Lists.newArrayList(), notCombines = Lists.newCopyOnWriteArrayList(names);
        for (int level = notCombines.size(); level != 0 && !notCombines.isEmpty(); level--) {
            final int nextLevel = level - 1;
            notCombines = Lists.partition(notCombines, level)
                    .parallelStream()
                    .filter(part -> part.size() >= nextLevel)
                    .flatMap(part -> {
                        String name = CommonUtils.spaceJoin(part);
                        boolean isContain = dictionaryService.isContain(name);
                        if (isContain) combines.add(name);
                        return isContain ? Stream.empty() : part.stream();
                    })
                    .collect(Collectors.toList());
        }
        return Lists.newArrayList(Iterables.concat(combines, notCombines));
    }

    private static List<String> generation(List<String> items, int level) {
        return IntStream.rangeClosed(level, items.size())
                .mapToObj(i -> items.subList(i - level, i))
                .map(CommonUtils::spaceJoin)
                .collect(Collectors.toList());
    }
}