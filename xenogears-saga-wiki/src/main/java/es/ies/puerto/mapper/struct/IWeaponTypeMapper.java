package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.WeaponTypeDTO;
import es.ies.puerto.model.entities.WeaponType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author mackstm
 */
@Mapper(uses = IWeaponMapper.class)
public interface IWeaponTypeMapper {
    IWeaponTypeMapper INSTANCE = Mappers.getMapper(IWeaponTypeMapper.class);
    WeaponType toEntity(WeaponTypeDTO weaponTypeDTO);
    WeaponTypeDTO toDTO(WeaponType weaponType);
}
