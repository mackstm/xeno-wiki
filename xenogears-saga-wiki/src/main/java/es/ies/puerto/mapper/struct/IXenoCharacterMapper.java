package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.model.entities.XenoCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
/**
 * @author mackstm
 */
@Mapper(uses = {IWeaponMapper.class, IMechMapper.class})
public interface IXenoCharacterMapper {
    IXenoCharacterMapper INSTANCE = Mappers.getMapper(IXenoCharacterMapper.class);
    XenoCharacter toEntity(XenoCharacterDTO xenoCharacterDTO);
    XenoCharacterDTO toDTO(XenoCharacter xenoCharacter);
}
