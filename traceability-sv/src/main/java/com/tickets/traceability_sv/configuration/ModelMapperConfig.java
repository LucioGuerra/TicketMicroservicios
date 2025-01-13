package com.tickets.traceability_sv.configuration;

import com.tickets.traceability_sv.entity.RequirementTraceability;
import com.tickets.traceability_sv.event.RequirementTraceabilityEvent;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<RequirementTraceabilityEvent, RequirementTraceability>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setCode(source.getCode());
                map().setDateTime(source.getDateTime());
                map().setUserId(source.getUserId());
                map().setEmail(source.getEmail());
                map().setAction(source.getAction());
            }
        });
        return modelMapper;
    }
}
