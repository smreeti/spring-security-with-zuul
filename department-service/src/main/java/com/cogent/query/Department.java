package com.cogent.query;

public class Department {
    public static String createQueryTofetchMinimalDepartmentData() {
        String sql = "SELECT id,department_name,code,status FROM department";
        return sql;
    }

}
