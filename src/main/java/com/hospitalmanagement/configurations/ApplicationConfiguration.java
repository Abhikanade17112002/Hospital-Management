package com.hospitalmanagement.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

@org.springframework.context.annotation.Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper getModelMapperBean(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)  // strict name matching
                .setFieldMatchingEnabled(true)                    // match by fields directly
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }
}
