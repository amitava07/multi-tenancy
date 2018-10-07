package com.multidatasource;

public class EmployeeContextHandler {
    private static final ThreadLocal<Tenant> contextHolder =
            new ThreadLocal<Tenant>();

    public static void setTenantType(Tenant tenant) {
        contextHolder.set(tenant);
    }

    public static Tenant getTenantType() {
        return (Tenant) contextHolder.get();
    }

    public static void clearTenantType() {
        contextHolder.remove();
    }
}
