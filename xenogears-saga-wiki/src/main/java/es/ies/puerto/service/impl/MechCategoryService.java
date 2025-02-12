package es.ies.puerto.service.impl;

import es.ies.puerto.dto.MechCategoryDTO;
import es.ies.puerto.mapper.struct.IMechCategoryMapper;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.model.db.dao.IMechCategoryRepository;
import es.ies.puerto.model.entities.MechCategory;
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
public class MechCategoryService implements IServiceJPA<MechCategoryDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MechCategoryService.class);

    private IMechCategoryRepository repository;

    /**
     * Default constructor of the class
     */
    public MechCategoryService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IMechCategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to add a MechCategoryDTO to the database
     * @param mechCategoryDTO MechCategoryDTO to add
     * @return boolean indicating if the operation was successful
     */
    @Override
    public boolean add(MechCategoryDTO mechCategoryDTO) {
        if (repository.existsById(mechCategoryDTO.getId())){
            return false;
        }
        repository.save(IMechCategoryMapper.INSTANCE.toEntity(mechCategoryDTO));
        return true;
    }

    /**
     * Updates a MechCategory in the database with the given information
     * @param id of the MechCategory to update
     * @param mechCategoryDTO with the new information
     * @return boolean indicating if the operation was successful
     * @throws Exception if the MechCategory with the given id does not exist
     */
    @Override
    public boolean update(int id, MechCategoryDTO mechCategoryDTO) throws Exception {
        try {
            MechCategory toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            MechCategory aux = IMechCategoryMapper.INSTANCE.toEntity(mechCategoryDTO);
            toUpdate.setName(aux.getName());
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    /**
     * Method to get all the MechCategoryDTO in the database
     * @return a List of MechCategoryDTO
     */
    @Override
    public List<MechCategoryDTO> getAll() {
        List<MechCategory> mechCategorys = repository.findAll();
        List<MechCategoryDTO> mechCategoryDTOS = new ArrayList<>();
        for (MechCategory mechCategory : mechCategorys){
            mechCategoryDTOS.add(IMechCategoryMapper.INSTANCE.toDTO(mechCategory));
        }
        return mechCategoryDTOS;
    }

    /**
     * Retrieves a MechCategoryDTO by its id
     * @param id of the MechCategoryDTO to retrieve
     * @return the MechCategoryDTO with the given id, or null if it does not exist
     */
    @Override
    public MechCategoryDTO getById(int id) {
        return IMechCategoryMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a MechCategory by its id
     * @param id of the MechCategory to delete
     * @return boolean indicating if the operation was successful
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
