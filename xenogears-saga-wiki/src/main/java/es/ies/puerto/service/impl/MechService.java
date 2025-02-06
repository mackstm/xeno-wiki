package es.ies.puerto.service.impl;

import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.mapper.struct.IMechMapper;
import es.ies.puerto.model.db.dao.IMechRepository;
import es.ies.puerto.model.entities.Mech;
import es.ies.puerto.service.interfaces.IServiceJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mackstm
 */
@Component
@Transactional()
public class MechService implements IServiceJPA<MechDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MechService.class);

    private IMechRepository repository;

    /**
     * Default constructor of the class
     */
    public MechService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IMechRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(MechDTO mechDTO) {
        if (mechDTO == null){
            return false;
        }
        repository.save(IMechMapper.INSTANCE.toEntity(mechDTO));
        return true;
    }

    @Override
    public boolean update(int id, MechDTO mechDTO) throws Exception {
        try {
            Mech toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            Mech aux = IMechMapper.INSTANCE.toEntity(mechDTO);
            if (aux.getName() != null){
                toUpdate.setName(aux.getName());
            }
            if (aux.getXenoCharacter() != null){
                toUpdate.setXenoCharacter(aux.getXenoCharacter());
            }
            if (aux.getMechCategory() != null){
                toUpdate.setMechCategory(aux.getMechCategory());
            }
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    @Override
    public List<MechDTO> getAll() {
        List<Mech> mechs = repository.findAll();
        List<MechDTO> mechDTOS = new ArrayList<>();
        for (Mech mech : mechs){
            mechDTOS.add(IMechMapper.INSTANCE.toDTO(mech));
        }
        return mechDTOS;
    }

    @Override
    public MechDTO getById(int id) {
        if (!repository.existsById(id)) {
            return null;
        }

        MechDTO result = null;

        List<MechDTO> list = getAll();

        for (MechDTO mechDTO : list){
            if (mechDTO.getId() == id){
                result = mechDTO;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;

    }
}
