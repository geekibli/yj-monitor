package com.yj.monitor.api.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author gaolei
 * @Date 2022/1/21 上午11:36
 * @Version 1.0
 */
public class UnsafeUtil {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
