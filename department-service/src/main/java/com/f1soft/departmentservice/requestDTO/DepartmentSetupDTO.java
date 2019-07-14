package com.f1soft.departmentservice.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Sauravi
 */


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class DepartmentSetupDTO implements Serializable {

    @ApiModelProperty(value = "Department name should be unique")
    private String departmentName;

    @ApiModelProperty(value = "Department Code should be unique")
    private String code;

    @ApiModelProperty(value = "Y, N and D are only used")
    private Character status;

}
