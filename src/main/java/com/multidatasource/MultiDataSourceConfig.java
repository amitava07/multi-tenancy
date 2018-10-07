package com.multidatasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiDataSourceConfig {

    @Bean
    public AbstractRoutingDataSource routingDataSource() throws NamingException {
        EmployeeRoutingDataSource routingDataSource = new EmployeeRoutingDataSource();

        Map<Object, Object> dataSourceMap= new HashMap<>();
        dataSourceMap.put(Tenant.US, getDataSource("jdbc/usDataSource"));
        dataSourceMap.put(Tenant.INDIA, getDataSource("jdbc/indDataSource"));

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(getDataSource("jdbc/indDataSource"));
        return routingDataSource;
    }

    private DataSource getDataSource(String jndiName) throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/" + jndiName);
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource)bean.getObject();
    }
}
