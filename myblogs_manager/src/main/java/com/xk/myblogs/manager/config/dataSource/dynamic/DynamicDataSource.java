package com.xk.myblogs.manager.config.dataSource.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: tian
 * @date: 2020/7/28 23:52
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }
}
