package com.tickets.comment_sv.configuration;

import com.tickets.comment_sv.dto.CommentDTO;
import com.tickets.comment_sv.dto.GetCommentDTO;
import com.tickets.comment_sv.entity.Comment;
import com.ticket.shared.service.FileService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private final FileService fileService;

    public ModelMapperConfig(FileService fileService) {
        this.fileService = fileService;
    }

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

        modelMapper.addMappings(new PropertyMap<Comment, GetCommentDTO>(){
            @Override
            protected void configure(){
                map().setId(source.getId());
                map().setDescription(source.getDescription());
                map().setSubject(source.getSubject());
                map().setCreatedAt(source.getCreatedAt());

                skip(destination.getUser());
            }
        });
        return modelMapper;
    }
}
