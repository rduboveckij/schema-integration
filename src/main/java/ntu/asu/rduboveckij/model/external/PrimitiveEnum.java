package ntu.asu.rduboveckij.model.external;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * @author andrus.god
 * @since 6/21/2014
 */
public enum PrimitiveEnum {
    ANY_SIMPLE_TYPE("anySimpleType"),
    STRING("string"),
    BOOLEAN("boolean", STRING),
    BASE_64_BINARY("base64Binary"),
    HEX_BINARY("hexBinary", BASE_64_BINARY),
    DECIMAL("decimal", STRING),
    DOUBLE("double", DECIMAL, STRING),
    FLOAT("float", DOUBLE, DECIMAL, STRING),
    ANY_URI("anyURI", STRING),
    DURATION("duration"),
    DATE_TIME("dateTime", DURATION),
    TIME("time", DATE_TIME, DURATION),
    DATE("date", DATE_TIME, DURATION),
    YEAR_MONTH("gYearMonth", DATE_TIME, DURATION),
    YEAR("gYear", YEAR_MONTH, DATE_TIME, DURATION),
    MONTH_DAY("gMonthDay", DATE_TIME, DURATION),
    DAY("gDay", MONTH_DAY, DATE_TIME, DURATION),
    MONTH("gMonth", YEAR_MONTH, MONTH_DAY, DATE_TIME, DURATION),
    INTEGER("integer", DECIMAL),
    LONG("long", INTEGER, DOUBLE, DECIMAL),
    INT("int", LONG, INTEGER, FLOAT, DOUBLE, DECIMAL),
    SHORT("short", INT, LONG, INTEGER, FLOAT, DOUBLE, DECIMAL),
    BYTE("byte", SHORT, INT, LONG, INTEGER, FLOAT, DOUBLE, DECIMAL),
    NORMALIZED_STRING("normalizedString", STRING, ANY_URI),
    TOKEN("token", NORMALIZED_STRING, STRING, ANY_URI),
    NON_POSITIVE_INTEGER("nonPositiveInteger", INTEGER, DECIMAL),
    NEGATIVE_INTEGER("negativeInteger", NON_POSITIVE_INTEGER, INTEGER, DECIMAL),
    NON_NEGATIVE_INTEGER("nonNegativeInteger", INTEGER, DECIMAL),
    UNSIGNED_LONG("unsignedLong", LONG, NON_POSITIVE_INTEGER, INTEGER, DOUBLE, DECIMAL),
    POSITIVE_INTEGER("positiveInteger", NEGATIVE_INTEGER, INTEGER, DECIMAL),
    UNSIGNED_INT("unsignedInt", INT, UNSIGNED_LONG, LONG, NON_POSITIVE_INTEGER, INTEGER, FLOAT, DOUBLE, DECIMAL),
    UNSIGNED_SHORT("unsignedShort", SHORT, UNSIGNED_INT, INT, UNSIGNED_LONG, LONG, NON_POSITIVE_INTEGER, INTEGER, FLOAT, DOUBLE, DECIMAL),
    UNSIGNED_BYTE("unsignedByte", UNSIGNED_SHORT, SHORT, UNSIGNED_INT, INT, UNSIGNED_LONG, LONG, NON_POSITIVE_INTEGER, INTEGER, FLOAT, DOUBLE, DECIMAL);

    private final String name;
    private final Set<PrimitiveEnum> castSet;

    PrimitiveEnum(String name, PrimitiveEnum... castSet) {
        this.name = name;
        this.castSet = Sets.newHashSet(castSet);
    }

    public String getName() {
        return name;
    }

    public boolean isCast(PrimitiveEnum primitive) {
        return this == primitive || this == ANY_SIMPLE_TYPE || primitive == ANY_SIMPLE_TYPE ||
                this.castSet.contains(primitive);
    }

    public static Optional<PrimitiveEnum> of(String name) {
        return Arrays.stream(values()).filter(value -> value.name.equals(name)).findAny();
    }
}