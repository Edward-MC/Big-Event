package com.projects.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {

    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // Get Value by Key
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
	
    // Store Key Pair
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    // Clean ThreadLocal Instance to Avoid Memory Leak
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
