package com.cogent.DTO.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author Sauravi
 */


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class DepartmentResponseDTO implements Serializable {

    @ApiModelProperty(value = "Department id i.e. auto genrated by database")
    private Long id;

    @ApiModelProperty(value = "Department name should be unique")
    private String departmentName;

    @ApiModelProperty(value = "Department Code should be unique")
    private String code;

    @ApiModelProperty(value = "Y, N and D are only used")
    private Character status;

}
