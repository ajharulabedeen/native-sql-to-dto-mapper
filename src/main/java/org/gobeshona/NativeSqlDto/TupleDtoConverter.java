package org.gobeshona.NativeSqlDto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Tuple;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
public class TupleDtoConverter {

    static Logger logger = LoggerFactory.getLogger(TupleDtoConverter.class);

    public static Object covertionAllString(Tuple t, Class s) throws Exception {
        Object instance = s.newInstance();
        Method[] methods = s.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("set")) {
                String name = method.getName().replace("set", "").toLowerCase();
                String value = "-";
                try {
                    value = Objects.toString(t.get(name), "-");
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    value = "-";
                    logger.warn("Setting Default value '-'");
                }
                methods[i].invoke(instance, value);
            }
        }
        return instance;
    }

    public static Object covertionAllAsType(Tuple tuple, Class s) throws Exception {
        Object instance = s.newInstance();
        Method[] methods = s.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("set")) {
                String name = method.getName().replace("set", "").toLowerCase();
                String value = "-";
                if (method.getParameterCount() == 1) {
                    Parameter parameter = method.getParameters()[0];
                    String paramType = parameter.getType().getName();
                    try {
                        if (paramType.equals(DataType.bytePrim) || paramType.equals(DataType.byteObject)) {
                            byte value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).byteValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.booleanPrim) || paramType.equals(DataType.booleanObject)) {
                            boolean value1 = ((Boolean) Optional.ofNullable(tuple.get(name)).orElse(0)).booleanValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.charPrim) || paramType.equals(DataType.charObject)) {
                            char value1 = ((Character) Optional.ofNullable(tuple.get(name)).orElse(0)).charValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.stringPrim) || paramType.equals(DataType.stringObject)) {
                            String value1 = ((String) Optional.ofNullable(tuple.get(name)).orElse("-").toString());
                            methods[i].invoke(instance, value1);
                        }
                        //Number Type
                        else if (paramType.equals(DataType.shortPrim) || paramType.equals(DataType.SHORT_NUMBER)) {
                            short value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).shortValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.INT) || paramType.equals(DataType.INTEGER_NUMBER) || paramType.equals(DataType.ATOMICINTEGER_NUMBER)) {
                            int value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).intValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.longPrim) || paramType.equals(DataType.LONG_NUMBER) || paramType.equals(DataType.BIGINTEGER_NUMBER)) {
                            long value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).longValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.floatPrim) || paramType.equals(DataType.floatObject)) {
                            float value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).floatValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.doublePrim) || paramType.equals(DataType.doubleObject)) {
                            Double value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).doubleValue();
                            methods[i].invoke(instance, value1);
                        } else if (paramType.equals(DataType.BIGDECIMAL_NUMBER)) {
//                            double value1 = ((Number) Optional.ofNullable(tuple.get(name)).orElse(0)).doubleValue();
                            String value1 = ((String) Optional.ofNullable(tuple.get(name)).orElse("0.0").toString());
                            BigDecimal bigDecimal = new BigDecimal(value1);
                            methods[i].invoke(instance, bigDecimal);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
//                        e.printStackTrace();
//                        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            }
        }
        return instance;
    }

    public static <T> List<T> convertListOfTuple(List<Tuple> tupleList, Class<T> dtoClass) {
        List<T> list = new ArrayList<>();
        tupleList.forEach(tuple -> {
            try {
                T dto = dtoClass.cast(covertionAllAsType(tuple, dtoClass));
                list.add(dto);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        });
        return list;
    }
}


