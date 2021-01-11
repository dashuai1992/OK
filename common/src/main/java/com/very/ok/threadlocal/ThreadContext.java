package com.very.ok.threadlocal;

public class ThreadContext {

    private static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取线程保存的数据
     * 2020-06-23 17:44
     * yds
     **/
    public static Object get(){
        return THREAD_LOCAL.get();
    }

    /**
     * 保存
     * 2020-06-23 17:45
     * yds
     **/
    public static void set(String val){
        THREAD_LOCAL.set(val);
    }

    /**
     * 释放
     * 2020-06-23 17:45
     * yds
     **/
    public static void release() {
        THREAD_LOCAL.remove();
    }

}
