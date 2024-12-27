package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.model.entities.Weapon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author mackstm
 */
@Mapper
public interface IWeaponMapper {
    IWeaponMapper INSTANCE = Mappers.getMapper(IWeaponMapper.class);
    Weapon toEntity(WeaponDTO weaponDTO);
    WeaponDTO toDTO(Weapon weapon);
}
