package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
/**
 * @author mackstm
 */
@Mapper
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toEntity(UserDTO userDTO);
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);
}
