package com.hcis.datas;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author zhw
 * @date 2019/12/25
 **/
public class MultipleDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDataSource();
    }
}
