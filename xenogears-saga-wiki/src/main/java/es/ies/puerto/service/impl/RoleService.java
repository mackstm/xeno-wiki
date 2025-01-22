package es.ies.puerto.service.impl;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.mapper.struct.IRoleMapper;
import es.ies.puerto.model.db.dao.IDaoRole;
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

    private IDaoRole repository;

    /**
     * Default constructor of the class
     */
    public RoleService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setIDaoRole(IDaoRole repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(RoleDTO roleDTO) {
        if (roleDTO == null){
            return false;
        }
        repository.save(IRoleMapper.INSTANCE.toEntity(roleDTO));
        return true;
    }

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


    @Override
    public List<RoleDTO> getAll() {
        List<Role> roles = repository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles){
            roleDTOS.add(IRoleMapper.INSTANCE.toDTO(role));
        }
        return roleDTOS;
    }

    @Override
    public RoleDTO getById(int id) {
        if (!repository.existsById(id)) {
            return null;
        }

        RoleDTO result = null;

        List<RoleDTO> list = getAll();

        for (RoleDTO roleDTO: list){
            if (roleDTO.getId() == id){
                result = roleDTO;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        if (!repository.existsById(id) || id == 1) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
