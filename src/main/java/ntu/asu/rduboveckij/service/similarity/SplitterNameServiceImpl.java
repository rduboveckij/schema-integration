package ntu.asu.rduboveckij.service.similarity;

import ntu.asu.rduboveckij.api.similarity.SplitterNameService;
import ntu.asu.rduboveckij.api.algorithm.SplitterTermService;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Split;
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
    public Split.Element split(Model.Element element) {
        return new Split.Element(element, splitterTermService.split(element.getName()), element.getAttributes()
                .parallelStream()
                .map(attribute -> new Split.Attribute(attribute, splitterTermService.split(attribute.getName())))
                .collect(Collectors.toSet()));
    }
}
