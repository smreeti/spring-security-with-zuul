package com.f1soft.departmentservice.constants;

public class WebResourceConstants {

    public static final String BASE_API = "/api";

    public static class DepartmentController{
        public static final String BASE_API_DEPARTMENT = "/department";

        public interface DEPARTMENTCRUD{
            String SAVE="/save";
            String RETRIEVE="/retrieve";
            String RETRIEVE_MINIMAL_DATA="/retrieveMinimalData";
            String DELETE="/delete/{id}";
            String UPDATE="/update";

        }


    }
}
