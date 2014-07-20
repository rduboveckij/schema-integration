package ntu.asu.rduboveckij.service;

import ntu.asu.rduboveckij.api.SplitterNameService;
import ntu.asu.rduboveckij.api.SplitterTermService;
import ntu.asu.rduboveckij.model.external.Element;
import ntu.asu.rduboveckij.model.internal.AttributeSplit;
import ntu.asu.rduboveckij.model.internal.ElementSplit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.stream.Collectors;

/**
 * @author andrus.god
 * @since 7/20/2014
 */
@Service
public class SplitterNameServiceImpl implements SplitterNameService {
    @Inject
    private SplitterTermService splitterTermService;

    @Override
    public ElementSplit split(Element element) {
        return new ElementSplit(element, splitterTermService.split(element.getName()), element.getAttributes()
                .parallelStream()
                .map(attribute -> new AttributeSplit(attribute, splitterTermService.split(attribute.getName())))
                .collect(Collectors.toSet()));
    }
}
