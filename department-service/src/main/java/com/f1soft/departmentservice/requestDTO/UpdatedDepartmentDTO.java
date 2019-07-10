package com.f1soft.departmentservice.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Sauravi
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedDepartmentDTO {
    private Long id;
    private String departmentName;
    private String code;
    private Character status;
}
