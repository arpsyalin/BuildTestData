package com.lyl.testobject;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestObjectBuildUtils {
    public static <T> List<T> getList(Class<T> clazz, int count) {
        List<T> arr = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arr.add(get(clazz));
        }
        return arr;
    }

    static String RANDOM_TEXT = "QWERTYUIOPASDFGHJKLZXCVBNM";

    public static String getRandomText(String[] arr) {
        String test = "";
        if (arr == null || arr.length == 0) {
            char[] chars = RANDOM_TEXT.toCharArray();
            for (int i = 0; i < 10; i++) {
                int index = (int) (Math.random() * RANDOM_TEXT.length());
                test += chars[index];
            }
        } else {
            int index = (int) (Math.random() * arr.length);
            test = arr[index];
        }
        return test;
    }

    public static boolean getRandomBoolean() {
        return Math.random() * 2 > 1 ? false : true;
    }

    public static int getRandomInteger(String[] arr) {
        if (arr == null || arr.length == 0) {
            return (int) (Math.random() * 180);
        } else {
            try {
                return Integer.parseInt(getRandomText(arr));
            } catch (Exception e) {
                return getRandomInteger(null);
            }
        }
    }

    public static byte getRandomByte(String[] arr) {
        if (arr == null || arr.length == 0) {
            return (byte) (Math.random() * 128);
        } else {
            try {
                return Byte.parseByte(getTextRandom(arr));
            } catch (Exception e) {
                return getRandomByte(null);
            }
        }
    }

    private static String getTextRandom(String[] arr) {
        if (arr == null || arr.length == 0) return "";
        int index = (int) (Math.random() * arr.length);
        return arr[index];
    }

    public static double getRandomFloat(String[] arr) {
        if (arr == null || arr.length == 0) {
            BigDecimal bigDecimal = new BigDecimal(Math.random() * 150);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            try {
                return Double.parseDouble(getRandomText(arr));
            } catch (Exception e) {
                return getRandomFloat(null);
            }
        }
    }

    public static <T> T get(Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                TestObject testObject = field.getAnnotation(TestObject.class);
                TestListType testListType = field.getAnnotation(TestListType.class);
                String[] arr = null;
                Class arrClass = null;
                if (testObject != null) {
                    arr = testObject.value();
                }
                if (testListType != null) {
                    arrClass = testListType.listType();
                }
                if (field.getType() == String.class) {
                    field.set(t, getRandomText(arr));
                } else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == Short.class || field.getType() == int.class || field.getType() == long.class || field.getType() == short.class) {
                    field.set(t, getRandomInteger(arr));
                } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                    field.set(t, getRandomBoolean());
                } else if (field.getType() == Float.class
                        || field.getType() == Double.class || field.getType() == float.class
                        || field.getType() == double.class
                        || "double".equals(field.getType().getName())) {
                    field.set(t, getRandomFloat(arr));
                } else if (field.getType() == Byte.class || field.getType() == byte.class) {
                    field.set(t, getRandomByte(arr));
                } else if (field.getType() == List.class) {
                    if (arrClass != null) {
                        field.set(t, getList(arrClass, 5));
                    }
                } else {
                    // TODO: 2021/9/16 暂时不使用
                    field.set(t, get(field.getType()));
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}