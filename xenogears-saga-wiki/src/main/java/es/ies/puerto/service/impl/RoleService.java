package es.ies.puerto.service.impl;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.mapper.struct.IRoleMapper;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.model.db.dao.IRoleRepository;
import es.ies.puerto.model.entities.Role;
import es.ies.puerto.service.interfaces.IServiceJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mackstm
 */
@Component
@Transactional()
public class RoleService implements IServiceJPA<RoleDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private IRoleRepository repository;

    /**
     * Default constructor of the class
     */
    public RoleService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IRoleRepository repository) {
        this.repository = repository;
    }

    /**
     * Adds a new Role to the repository.
     *
     * @param roleDTO the RoleDTO object to be added
     * @return true if the Role was successfully added, false if the RoleDTO is null
     */
    @Override
    public boolean add(RoleDTO roleDTO) {
        if (repository.existsById(roleDTO.getId())){
            return false;
        }
        repository.save(IRoleMapper.INSTANCE.toEntity(roleDTO));
        return true;
    }

    /**
     * Updates a Role in the repository.
     *
     * @param id the identifier of the Role to be updated
     * @param roleDTO the RoleDTO object containing the updated data
     * @return true if the Role was successfully updated, false if the RoleDTO is null
     * @throws Exception if the Role with the given id is not found
     */
    @Override
    public boolean update(int id, RoleDTO roleDTO) throws Exception {
        try {
            Role toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            Role aux = IRoleMapper.INSTANCE.toEntity(roleDTO);
            toUpdate.setName(aux.getName());
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    /**
     * Gets all the Roles from the repository.
     *
     * @return a List containing all the Roles in the repository
     */
    @Override
    public List<RoleDTO> getAll() {
        List<Role> roles = repository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles){
            roleDTOS.add(IRoleMapper.INSTANCE.toDTO(role));
        }
        return roleDTOS;
    }

    /**
     * Gets a Role from the repository by its id.
     *
     * @param id the identifier of the Role to be retrieved
     * @return the RoleDTO object with the given id, or null if no such Role exists
     */
    @Override
    public RoleDTO getById(int id) {
        return IRoleMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a Role from the repository by its id.
     *
     * @param id the identifier of the Role to be deleted
     * @return true if the Role was successfully deleted, false if the Role does not exist or is an admin role
     */
    @Override
    public boolean delete(int id) {
        if (!repository.existsById(id) || id == 1) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
