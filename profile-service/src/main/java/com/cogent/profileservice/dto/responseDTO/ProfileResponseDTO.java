package com.cogent.profileservice.dto.responseDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author smriti on 7/15/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponseDTO implements Serializable{

    private String description;

}
