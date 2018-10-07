package com.example.demo.helpers;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import javax.sql.DataSource;

public class ServerContainerFactory extends TomcatEmbeddedServletContainerFactory {

    @Override
    protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
            Tomcat tomcat) {
        //Enables JNDI naming which is disabled by default
        tomcat.enableNaming();
        return super.getTomcatEmbeddedServletContainer(tomcat);
    }

    @Override
    protected void postProcessContext(Context context) {
        ContextResource resource = new ContextResource();
        resource.setName("jdbc/indDataSource");
        resource.setType(DataSource.class.getName());
        //resource.setProperty("driverClassName", "your.db.Driver");
        resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
        resource.setProperty("url", "jdbc:mysql://localhost:3306/employee_ind");
        resource.setProperty("username", "root");
        resource.setProperty("password", "Amarta_1981");
        context.getNamingResources().addResource(resource);

        resource = new ContextResource();
        resource.setName("jdbc/usDataSource");
        resource.setType(DataSource.class.getName());
        resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
        //resource.setProperty("driverClassName", "your.db.Driver");
        resource.setProperty("url", "jdbc:mysql://localhost:3306/employee_us");
        resource.setProperty("username", "root");
        resource.setProperty("password", "Amarta_1981");
        context.getNamingResources().addResource(resource);
    }

}
