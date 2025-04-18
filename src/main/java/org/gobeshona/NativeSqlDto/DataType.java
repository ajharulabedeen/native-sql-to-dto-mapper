package org.gobeshona.NativeSqlDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.*;

public class DataType {
    public static String bytePrim = "byte";
    public static String shortPrim = "short";
    public static String INT = "int";
    public static String longPrim = "long";
    public static String floatPrim = "float";
    public static String doublePrim = "double";
    public static String booleanPrim = "boolean";
    public static String charPrim = "char";
    public static String stringPrim = "String";

    public static String byteObject = Byte.class.getName();
    public static String booleanObject = Boolean.class.getName();
    public static String charObject = Character.class.getName();
    public static String stringObject = String.class.getName();

    public static String intObject = Integer.class.getName();
    public static String shortObject = Short.class.getName();
    public static String longObject = Long.class.getName();
    public static String floatObject = Float.class.getName();
    public static String doubleObject = Double.class.getName();

    public static String ATOMICINTEGER_NUMBER = AtomicInteger.class.getName();
    public static String ATOMICLONG_NUMBER = AtomicLong.class.getName();
    public static String BIGDECIMAL_NUMBER = BigDecimal.class.getName();
    public static String BIGINTEGER_NUMBER = BigInteger.class.getName();
    public static String BYTE_NUMBER = Byte.class.getName();
    public static String DOUBLE_NUMBER = Double.class.getName();
    public static String DOUBLEACCUMULATOR_NUMBER = DoubleAccumulator.class.getName();
    public static String DOUBLEADDER_NUMBER = DoubleAdder.class.getName();
    public static String FLOAT_NUMBER = Float.class.getName();
    public static String INTEGER_NUMBER = Integer.class.getName();
    public static String LONG_NUMBER = Long.class.getName();
    public static String LONGACCUMULATOR_NUMBER = LongAccumulator.class.getName();
    public static String LONGADDER_NUMBER = LongAdder.class.getName();
    public static String SHORT_NUMBER = Short.class.getName();


}

