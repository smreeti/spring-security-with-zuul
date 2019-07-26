package com.cogent.query;

import java.util.function.Supplier;

public class SubDepartment {
    public static Supplier<String> createQueryTofetchMinimalSubDepartmentData = () ->
            "SELECT id,name,code,status,department_id FROM sub_department";
}
