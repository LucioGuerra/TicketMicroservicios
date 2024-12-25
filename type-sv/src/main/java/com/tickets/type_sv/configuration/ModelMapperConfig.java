package com.tickets.type_sv.configuration;

import com.tickets.type_sv.dto.request.CategoryDTO;
import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.dto.response.GetCategoryDTO;
import com.tickets.type_sv.dto.response.GetTypeDTO;
import com.tickets.type_sv.entity.Category;
import com.tickets.type_sv.entity.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

        // Ajusta la estrategia para que no intente "adivinar" nada raro
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // Registra un nuevo mapeo manual
        modelMapper.addMappings(new PropertyMap<CategoryDTO, Category>() {
            @Override
            protected void configure() {
                // Mapeamos "description"
                map().setDescription(source.getDescription());

                // Omitimos "type"
                skip().setType(null);

                // Si quieres omitir más cosas, aquí mismo:
                // skip().setId(null);
                // skip().setDeleted(null);
                // skip().setCreatedAt(null);
                // skip().setUpdatedAt(null);
            }
        });


        return modelMapper;
    }
}
