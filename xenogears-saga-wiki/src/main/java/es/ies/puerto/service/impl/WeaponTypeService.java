package es.ies.puerto.service.impl;

import es.ies.puerto.dto.WeaponTypeDTO;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.model.db.dao.IDaoWeaponType;
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

    private IDaoWeaponType repository;

    /**
     * Default constructor of the class
     */
    public WeaponTypeService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setiDaoMGSCharacter(IDaoWeaponType repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(WeaponTypeDTO weaponTypeDTO) {
        if (weaponTypeDTO == null){
            return false;
        }
        repository.save(IWeaponTypeMapper.INSTANCE.toEntity(weaponTypeDTO));
        return true;
    }

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


    @Override
    public List<WeaponTypeDTO> getAll() {
        List<WeaponType> weaponTypes = repository.findAll();
        List<WeaponTypeDTO> weaponTypeDTOS = new ArrayList<>();
        for (WeaponType weaponType : weaponTypes){
            weaponTypeDTOS.add(IWeaponTypeMapper.INSTANCE.toDTO(weaponType));
        }
        return weaponTypeDTOS;
    }

    @Override
    public WeaponTypeDTO getById(int id) {
        if (!repository.existsById(id)) {
            return null;
        }

        WeaponTypeDTO result = null;

        List<WeaponTypeDTO> list = getAll();

        for (WeaponTypeDTO weaponTypeDTO : list){
            if (weaponTypeDTO.getId() == id){
                result = weaponTypeDTO;
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
