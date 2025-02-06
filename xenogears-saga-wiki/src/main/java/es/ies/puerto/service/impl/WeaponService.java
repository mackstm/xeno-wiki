package es.ies.puerto.service.impl;

import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.mapper.struct.IWeaponMapper;
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

    @Override
    public boolean add(WeaponDTO weaponDTO) {
        if (weaponDTO == null){
            return false;
        }
        repository.save(IWeaponMapper.INSTANCE.toEntity(weaponDTO));
        return true;
    }

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


    @Override
    public List<WeaponDTO> getAll() {
        List<Weapon> weapons = repository.findAll();
        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        for (Weapon weapon : weapons){
            weaponDTOS.add(IWeaponMapper.INSTANCE.toDTO(weapon));
        }
        return weaponDTOS;
    }

    @Override
    public WeaponDTO getById(int id) {
        if (!repository.existsById(id)) {
            return null;
        }

        WeaponDTO result = null;

        List<WeaponDTO> list = getAll();

        for (WeaponDTO weaponDTO : list){
            if (weaponDTO.getId() == id){
                result = weaponDTO;
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
