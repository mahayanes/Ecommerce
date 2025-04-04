package com.natixis.ecommerce.mapper;

import com.natixis.ecommerce.dto.request.UserRequestDTO;
import com.natixis.ecommerce.dto.response.UserResponseDTO;
import com.natixis.ecommerce.model.Role;
import com.natixis.ecommerce.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

     UserResponseDTO mapToUserResponseDTO(User user) ;

     List<UserResponseDTO> mapToUserResponseDTO(List<User> users);


     User mapToUser(UserRequestDTO userRequestDTO);

}


