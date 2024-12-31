package com.tickets.requirement_sv.configuration;

import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.entity.Requirement;
import org.apache.kafka.common.network.Mode;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<RequirementDTO, Requirement>() {
            @Override
            protected void configure() {
              map().setCategoryId(source.getCategoryId());
              map().setTypeId(source.getTypeId());
              map().setCreatorId(source.getCreatorId());
              map().setDescription(source.getDescription());
              map().setPriority(source.getPriority());
              map().setSubject(source.getSubject());
              map().setRequirements(new HashSet<>());


              skip(destination.getCreatedAt());
              skip(destination.getUpdatedAt());
              skip(destination.getAssigneeId());
              skip(destination.getCode());
              skip(destination.getId());
              skip(destination.getIsDeleted());
              skip(destination.getState());
            }

        });

        return modelMapper;
    }
}
