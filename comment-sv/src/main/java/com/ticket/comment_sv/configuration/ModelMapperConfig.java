package com.ticket.comment_sv.configuration;

import com.ticket.comment_sv.dto.CommentDTO;
import com.ticket.comment_sv.entity.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<CommentDTO, Comment>(){
            @Override
            protected void configure(){
                map().setDescription(source.getDescription());
                map().setSubject(source.getSubject());
                map().setRequirementId(source.getRequirementId());
                map().setUserId(source.getUserId());


                skip(destination.getCreatedAt());
                skip(destination.getUpdatedAt());
                skip(destination.getFiles());
                skip(destination.getId());
            }
        });
        return new ModelMapper();
    }
}
