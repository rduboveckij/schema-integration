package ntu.asu.rduboveckij;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.DataType;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.external.PrimitiveEnum;
import ntu.asu.rduboveckij.model.internal.Split;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @author andrus.god
 * @since 16.06.2014
 */
@ContextConfiguration(classes = ApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ApplicationConfigurationTest {
    protected static final Split.Element SPLIT_USER_BASKET;
    protected static final Split.Element SPLIT_CUSTOMER;

    static {
        Model.Attribute sourceAttr = new Model.Attribute("userId", DataType.valueOf(PrimitiveEnum.LONG.getName()));
        Model.Element sourceElem = new Model.Element("userBasket", Sets.newHashSet(sourceAttr));
        SPLIT_USER_BASKET = new Split.Element(sourceElem, Arrays.asList("user", "basket"),
                Sets.newHashSet(new Split.Attribute(sourceAttr, Arrays.asList("user", "id"))));

        Model.Attribute targetAttr = new Model.Attribute("customerId", DataType.valueOf(PrimitiveEnum.INTEGER.getName()));
        Model.Element targetElem = new Model.Element("customer", Sets.newHashSet(targetAttr));
        SPLIT_CUSTOMER = new Split.Element(targetElem, Arrays.asList("customer"),
                Sets.newHashSet(new Split.Attribute(targetAttr, Arrays.asList("customer", "id"))));
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}