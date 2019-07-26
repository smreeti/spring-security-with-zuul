package com.cogent.loginservice.constants;

/**
 * This class includes the name and API end points of other microservices that we need to communicate.
 * NOTE: WRITE EVERYTHING IN ALPHABETICAL ORDER
 */
public class MicroServiceConstants {

    public static final String BASE_API = "/api";

    public interface AdminMicroServiceConstants {
        String BASE = "admin-service";
        String SEARCH_ADMIN = "/search";
        String UPDATE_ADMIN = "/update";
    }
}
