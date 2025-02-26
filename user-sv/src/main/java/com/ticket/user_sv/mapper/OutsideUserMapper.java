package com.ticket.user_sv.mapper;

import com.ticket.user_sv.DTO.request.OutsideUserDTO;
import com.ticket.user_sv.DTO.response.GetOutsideUserDTO;
import com.ticket.user_sv.entity.OutsideUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OutsideUserMapper {

    OutsideUser toEntity(OutsideUserDTO dto);

    GetOutsideUserDTO toDTO(OutsideUser outsideUser);
}
