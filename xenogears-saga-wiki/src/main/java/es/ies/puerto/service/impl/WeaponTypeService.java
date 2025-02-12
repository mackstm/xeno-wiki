package es.ies.puerto.service.impl;

import es.ies.puerto.dto.WeaponTypeDTO;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.mapper.struct.IXenoCharacterMapper;
import es.ies.puerto.model.db.dao.IWeaponTypeRepository;
import es.ies.puerto.model.entities.WeaponType;
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
public class WeaponTypeService implements IServiceJPA<WeaponTypeDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(WeaponTypeService.class);

    private IWeaponTypeRepository repository;

    /**
     * Default constructor of the class
     */
    public WeaponTypeService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IWeaponTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to add a WeaponTypeDTO to the database.
     * @param weaponTypeDTO The object to be added.
     * @return True if the object was added, false if it wasn't.
     */
    @Override
    public boolean add(WeaponTypeDTO weaponTypeDTO) {
        if (repository.existsById(weaponTypeDTO.getId())){
            return false;
        }
        repository.save(IWeaponTypeMapper.INSTANCE.toEntity(weaponTypeDTO));
        return true;
    }

    /**
     * Updates a WeaponTypeDTO in the database.
     * @param id The identifier of the element to be updated.
     * @param weaponTypeDTO The new data for the element.
     * @return True if the update was successful, false otherwise.
     * @throws Exception if the element with the given id does not exist.
     */
    @Override
    public boolean update(int id, WeaponTypeDTO weaponTypeDTO) throws Exception {
        try {
            WeaponType toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            WeaponType aux = IWeaponTypeMapper.INSTANCE.toEntity(weaponTypeDTO);
            toUpdate.setName(aux.getName());
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    /**
     * Retrieves all the elements of the repository.
     * @return A List containing all the elements of the repository.
     */
    @Override
    public List<WeaponTypeDTO> getAll() {
        List<WeaponType> weaponTypes = repository.findAll();
        List<WeaponTypeDTO> weaponTypeDTOS = new ArrayList<>();
        for (WeaponType weaponType : weaponTypes){
            weaponTypeDTOS.add(IWeaponTypeMapper.INSTANCE.toDTO(weaponType));
        }
        return weaponTypeDTOS;
    }

    /**
     * Retrieves a WeaponTypeDTO by its id.
     * @param id The identifier of the WeaponTypeDTO to be retrieved.
     * @return The WeaponTypeDTO with the given id, or null if it does not exist.
     */
    @Override
    public WeaponTypeDTO getById(int id) {
        return IWeaponTypeMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a WeaponTypeDTO from the repository by its id.
     *
     * @param id The identifier of the WeaponTypeDTO to be deleted.
     * @return True if the WeaponTypeDTO was deleted successfully, false otherwise.
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
