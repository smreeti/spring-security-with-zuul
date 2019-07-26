package com.cogent.adminservice.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable{
    private List<AdminResponseDTO> adminResponseDTOS;

    private String message;
}
