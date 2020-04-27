package com.hcis.datas;

/**
 * @author zhw
 * @date 2019/12/25
 **/

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value();

    /**
     * 第一个数据源
     */
    String DATA_SOURCE_FIR = "dataSource1";

    /**
     * 第二个数据源
     */
    String DATA_SOURCE_SEC = "dataSource2";
}
