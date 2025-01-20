package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.model.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * @author mackstm
 */
@Mapper(uses = IUserMapper.class)
public interface IRoleMapper {
    IRoleMapper INSTANCE = Mappers.getMapper(IRoleMapper.class);

    Role toEntity(RoleDTO roleDTO);
    RoleDTO toDTO(Role role);
}
