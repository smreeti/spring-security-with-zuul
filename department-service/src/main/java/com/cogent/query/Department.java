package com.cogent.query;

import java.util.function.Supplier;

public class Department {
    public static Supplier<String> createQueryTofetchMinimalDepartmentData = () ->
            "SELECT id,department_name,code,status FROM department";
}
