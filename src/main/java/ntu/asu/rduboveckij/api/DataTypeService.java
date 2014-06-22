package ntu.asu.rduboveckij.api;

import ntu.asu.rduboveckij.model.DataType;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
public interface DataTypeService {
    DataType valueOf(String name);
}
