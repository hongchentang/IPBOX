package com.hcis.datas;

/**
 * @author zhw
 * @date 2019/12/25
 **/
public class DbContextHolder {
    /**
     *
     */
    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();

    /**
     * 设置当前数据库
     */
    static void setDataSource(String dataSource){
        THREAD_DATA_SOURCE.set(dataSource);
    }

    /**
     * 取得当前数据库
     */
    static String getDataSource(){
        return THREAD_DATA_SOURCE.get();
    }

    /**
     * 清除上下文
     */
    static void cleanDataSource(){
        THREAD_DATA_SOURCE.remove();
    }

    public static ThreadLocal getThread(){
        return THREAD_DATA_SOURCE;
    }
}
