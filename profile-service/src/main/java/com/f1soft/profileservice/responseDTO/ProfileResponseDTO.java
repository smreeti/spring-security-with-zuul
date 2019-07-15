package com.f1soft.profileservice.responseDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author smriti on 7/15/19
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDTO implements Serializable{

    private String description;

}
