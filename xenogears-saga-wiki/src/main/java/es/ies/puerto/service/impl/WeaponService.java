package es.ies.puerto.service.impl;

import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.mapper.struct.IWeaponMapper;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.model.db.dao.IWeaponRepository;
import es.ies.puerto.model.entities.Weapon;
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
public class WeaponService implements IServiceJPA<WeaponDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(WeaponService.class);

    private IWeaponRepository repository;

    /**
     * Default constructor of the class
     */
    public WeaponService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IWeaponRepository repository) {
        this.repository = repository;
    }

/**
 * Adds a new Weapon to the repository.
 *
 * @param weaponDTO the WeaponDTO object to be added
 * @return true if the Weapon was successfully added, false if the WeaponDTO is null
 */
    @Override
    public boolean add(WeaponDTO weaponDTO) {
        if (repository.existsById(weaponDTO.getId())){
            return false;
        }
        repository.save(IWeaponMapper.INSTANCE.toEntity(weaponDTO));
        return true;
    }

/**
 * Updates an existing Weapon entity in the repository with the data from the provided WeaponDTO.
 *
 * @param id the identifier of the Weapon to be updated
 * @param weaponDTO the data transfer object containing updated data for the Weapon
 * @return true if the update was successful, false if an exception occurred
 * @throws Exception if the Weapon with the given id does not exist
 */
    @Override
    public boolean update(int id, WeaponDTO weaponDTO) throws Exception {
        try {
            Weapon toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            Weapon aux = IWeaponMapper.INSTANCE.toEntity(weaponDTO);
            if (aux.getName() != null) {
                toUpdate.setName(aux.getName());
            }
            if (aux.getXenoCharacter() != null) {
                toUpdate.setXenoCharacter(aux.getXenoCharacter());
            }
            if (aux.getWeaponType() != null) {
                toUpdate.setWeaponType(aux.getWeaponType());
            }
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


/**
 * Retrieves all Weapon entities from the repository and converts them to WeaponDTOs.
 *
 * @return a List of WeaponDTO objects representing all weapons in the repository
 */
    @Override
    public List<WeaponDTO> getAll() {
        List<Weapon> weapons = repository.findAll();
        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        for (Weapon weapon : weapons){
            weaponDTOS.add(IWeaponMapper.INSTANCE.toDTO(weapon));
        }
        return weaponDTOS;
    }

/**
 * Retrieves a WeaponDTO from the repository by its identifier.
 *
 * @param id the identifier of the WeaponDTO to be retrieved
 * @return the WeaponDTO object with the given id, or null if no such Weapon exists
 */
    @Override
    public WeaponDTO getById(int id) {
        return IWeaponMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a WeaponDTO object from the database.
     *
     * @param id the identifier of the WeaponDTO to be deleted
     * @return true if the WeaponDTO was deleted successfully, false otherwise
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
