package com.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class EmployeeRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return EmployeeContextHandler.getTenantType();
    }
}