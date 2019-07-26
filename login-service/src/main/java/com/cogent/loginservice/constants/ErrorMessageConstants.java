package com.cogent.loginservice.constants;

/*THIS CLASS CONTAINS CUSTOM ERROR MESSAGES FOR VARIOUS EXCEPTIONS*/
public class ErrorMessageConstants {

    public interface ForgetPassword {
        String DEVELOPER_MESSAGE = "Password didn't match with the original one.";
        String MESSAGE = "Incorrect password.Forgot Password?";
    }

    public interface IncorrectPasswordAttempts {
        String DEVELOPER_MESSAGE = "Admin is blocked with status 'B'";
        String MESSAGE = "Admin is blocked. Please contact your system administrator.";
    }

    public interface InvalidAdminStatus {
        String DEVELOPER_MESSAGE_FOR_BLOCKED = "The admin has status 'B'";
        String DEVELOPER_MESSAGE_FOR_INACTIVE = "The admin has status 'N'";
        String MESSAGE_FOR_BLOCKED = "The admin is in blocked state.";
        String MESSAGE_FOR_INACTIVE = "The admin is in inactive state.";
    }

    public interface InvalidAdminUsername {
        String DEVELOPER_MESSAGE = "Admin entity returned null";
        String MESSAGE = "Admin with given username doesn't exits.";
    }
}
