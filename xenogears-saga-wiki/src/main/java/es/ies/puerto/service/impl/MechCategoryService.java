package es.ies.puerto.service.impl;

import es.ies.puerto.dto.MechCategoryDTO;
import es.ies.puerto.mapper.struct.IMechCategoryMapper;
import es.ies.puerto.model.db.dao.IDaoMechCategory;
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

    private IDaoMechCategory repository;

    /**
     * Default constructor of the class
     */
    public MechCategoryService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setiDaoMGSCharacter(IDaoMechCategory repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(MechCategoryDTO mechCategoryDTO) {
        if (mechCategoryDTO == null){
            return false;
        }
        repository.save(IMechCategoryMapper.INSTANCE.toEntity(mechCategoryDTO));
        return true;
    }

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


    @Override
    public List<MechCategoryDTO> getAll() {
        List<MechCategory> mechCategorys = repository.findAll();
        List<MechCategoryDTO> mechCategoryDTOS = new ArrayList<>();
        for (MechCategory mechCategory : mechCategorys){
            mechCategoryDTOS.add(IMechCategoryMapper.INSTANCE.toDTO(mechCategory));
        }
        return mechCategoryDTOS;
    }

    @Override
    public MechCategoryDTO getById(int id) {
        if (!repository.existsById(id)) {
            return null;
        }

        MechCategoryDTO result = null;

        List<MechCategoryDTO> list = getAll();

        for (MechCategoryDTO mechCategoryDTO : list){
            if (mechCategoryDTO.getId() == id){
                result = mechCategoryDTO;
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
