package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.MechCategoryDTO;
import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.model.entities.MechCategory;
import es.ies.puerto.model.entities.XenoCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author mackstm
 */
@Mapper(uses = IMechMapper.class)
public interface IMechCategoryMapper {
    IMechCategoryMapper INSTANCE = Mappers.getMapper(IMechCategoryMapper.class);
    MechCategory toEntity(MechCategoryDTO mechCategoryDTO);
    MechCategoryDTO toDTO(MechCategory mechCategory);
}
