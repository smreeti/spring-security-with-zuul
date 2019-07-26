package com.cogent.adminservice.utility;

import org.modelmapper.ModelMapper;

public class MapperUtility {
    public static <T> T map(Object source, Class<T> destination) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, destination);
    }
}


