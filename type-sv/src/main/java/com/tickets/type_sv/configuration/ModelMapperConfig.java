package com.tickets.type_sv.configuration;

import com.tickets.type_sv.dto.request.CategoryDTO;
import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.dto.response.GetCategoryDTO;
import com.tickets.type_sv.dto.response.GetTypeDTO;
import com.tickets.type_sv.entity.Category;
import com.tickets.type_sv.entity.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        /*TypeMap<Category, GetCategoryDTO> categoryToGetCategoryDTO = modelMapper.createTypeMap(Category.class, GetCategoryDTO.class);
        categoryToGetCategoryDTO.addMappings(mapper ->
                mapper.map(src -> modelMapper.map(src.getType(), GetTypeDTO.class), GetCategoryDTO::setType));
*/
        /*TypeMap<Type, GetTypeDTO> typeToGetTypeDTO = modelMapper.createTypeMap(Type.class, GetTypeDTO.class);
        typeToGetTypeDTO.addMappings(mapper -> {
           mapper.map(Type::getId, GetTypeDTO::setId);
           mapper.map(Type::getCode, GetTypeDTO::setCode);
           mapper.map(Type::getDescription, GetTypeDTO::setDescription);
        });*/

        return modelMapper;
    }
}
