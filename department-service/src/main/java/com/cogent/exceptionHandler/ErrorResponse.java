package com.cogent.exceptionHandler;

import lombok.Builder;
import lombok.Data;

/**
 * @author Sauravi
 */

@Data
@Builder
public class ErrorResponse {
    private String errorMsg;
}
