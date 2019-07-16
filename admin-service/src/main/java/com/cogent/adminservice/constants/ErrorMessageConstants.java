package com.cogent.adminservice.constants;

/**
 * @author smriti
 */
public class ErrorMessageConstants {

    public interface AdminNotFoundException{
        String DEVELOPER_MESSAGE = "Admin entity returned null";
        String MESSAGE = "Admin with given parameters doesn't exits.";
    }
}
