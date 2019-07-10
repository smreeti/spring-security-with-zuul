package com.f1soft.departmentservice.requestDTO;

import lombok.Builder;
import lombok.Data;
/**
 * @author Sauravi
 */

@Data
@Builder
public class DepartmentSetupDTO {
    private String departmentName;
    private String code;
    private Character status;
}
