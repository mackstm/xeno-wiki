package es.ies.puerto.mapper.struct;

import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.model.entities.Mech;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author mackstm
 */
@Mapper
public interface IMechMapper {
    IMechMapper INSTANCE = Mappers.getMapper(IMechMapper.class);
    Mech toEntity(MechDTO mechDTO);
    MechDTO toDTO(Mech mech);
}
