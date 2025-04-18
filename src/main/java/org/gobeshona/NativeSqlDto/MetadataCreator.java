package org.gobeshona.NativeSqlDto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MetadataCreator {

    public List<ObjectMetaData> createMetadata(Object object) {
        List<ObjectMetaData> metadataList = new ArrayList<>();

        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        List<String> methodList = getMethodsContainingGet(objectClass);

        for (int i = 0; i < fields.length; i++) {
            ObjectMetaData metadata = new ObjectMetaData();
            metadata.setFieldName(fields[i].getName());
            metadata.setDataType(fields[i].getType().getName());
            metadata.setGetterMethodName(getGetterMethodName(fields[i].getName()));
//            metadata.setGetterMethodName(getMethodName(methodList, fields[i].getName()));
            metadataList.add(metadata);
        }
        return metadataList;
    }

    public String getMethodName(List<String> list, String fieldName) {
        String methodName = "";
        for (String s : list) {
            if (s.toLowerCase().contains(fieldName.toLowerCase())) {
                methodName = s;
            }
        }
        return methodName;
    }

    public List<String> getMethodsContainingGet(Class<?> clazz) {
        List<String> getList = new ArrayList<>();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.contains("get")) {
                getList.add(methodName);
            }
        }

        return getList;
    }

    private String getGetterMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /*
    * keep for testing puporse
    * todo: rm - have to remove later.
    * */

//    public static void main(String[] args) {
//        // Example usage
//        Object object = new MyClass();
//        MetadataCreator metadataCreator = new MetadataCreator();
//        List<ObjectMetaData> metadataList = metadataCreator.createMetadata(object);
//
//        // Print metadata
//        for (ObjectMetaData metadata : metadataList) {
//            System.out.println(metadata.getFieldName());
//            System.out.println(metadata.getDataType());
//            System.out.println(metadata.getGetterMethodName());
//            System.out.println();
//        }
//    }
}

