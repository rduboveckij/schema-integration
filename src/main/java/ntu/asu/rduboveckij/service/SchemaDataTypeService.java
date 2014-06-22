package ntu.asu.rduboveckij.service;

import com.google.common.collect.Maps;
import ntu.asu.rduboveckij.api.DataTypeService;
import ntu.asu.rduboveckij.model.DataType;
import ntu.asu.rduboveckij.model.PrimitiveEnum;
import ntu.asu.rduboveckij.model.PrimitiveType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
@Service
public class SchemaDataTypeService implements DataTypeService {
    private final Map<String, PrimitiveType> primitiveTypes = Maps.newHashMap();

    @PostConstruct
    public void init() {
       // primitiveTypes.put("string", new PrimitiveType("string"));
    }

    @Override
    public DataType valueOf(String name) {
        Optional<PrimitiveEnum> of = PrimitiveEnum.of(name);
        return null;
    }
}
