package com.cogent.DTO.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class UpdatedDepartmentDTO {

    @ApiModelProperty(value = "Department id i.e. auto genrated by database")
    private Long id;

    @ApiModelProperty(value = "Department name should be unique")
    private String departmentName;

    @ApiModelProperty(value = "Department Code should be unique")
    private String code;

    @ApiModelProperty(value = "Y, N and D are only used")
    private Character status;
}
