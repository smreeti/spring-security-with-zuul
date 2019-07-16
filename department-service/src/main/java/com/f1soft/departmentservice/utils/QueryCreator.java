package com.f1soft.departmentservice.utils;

public class QueryCreator {
    public static String createQueryTofetchMinimalDepartmentData(){
          String sql="SELECT id,department_name,code,status FROM department";
          return sql;
    }


}
