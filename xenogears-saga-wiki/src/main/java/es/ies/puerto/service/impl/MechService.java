package es.ies.puerto.service.impl;

import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.mapper.struct.IMechMapper;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
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

    /**
     * Method to add a MechDTO to the database
     * @param mechDTO the mechDTO to add
     * @return true if the MechDTO was added successfully, false otherwise
     */
    @Override
    public boolean add(MechDTO mechDTO) {
        if (repository.existsById(mechDTO.getId())){
            return false;
        }
        repository.save(IMechMapper.INSTANCE.toEntity(mechDTO));
        return true;
    }

/**
 * Updates an existing Mech entity in the database with the given MechDTO.
 *
 * @param id the identifier of the Mech to be updated
 * @param mechDTO the data transfer object containing updated data
 * @return true if the update was successful, false otherwise
 * @throws Exception if the Mech with the given id does not exist
 */
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


    /**
     * Returns a list of all MechDTO objects in the database.
     *
     * @return a list of MechDTO objects
     */
    @Override
    public List<MechDTO> getAll() {
        List<Mech> mechs = repository.findAll();
        List<MechDTO> mechDTOS = new ArrayList<>();
        for (Mech mech : mechs){
            mechDTOS.add(IMechMapper.INSTANCE.toDTO(mech));
        }
        return mechDTOS;
    }

    /**
     * Retrieves a MechDTO from the database by its identifier.
     *
     * @param id the identifier of the MechDTO to be retrieved
     * @return a MechDTO object if it exists, null otherwise
     */
    @Override
    public MechDTO getById(int id) {
        return IMechMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a MechDTO object from the database.
     *
     * @param id the identifier of the MechDTO to be deleted
     * @return true if the MechDTO was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;

    }
}
