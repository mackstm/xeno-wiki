package es.ies.puerto.service.impl;

import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.mapper.struct.IXenoCharacterMapper;
import es.ies.puerto.model.db.dao.IXenoCharacterRepository;
import es.ies.puerto.model.entities.XenoCharacter;
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
public class XenoCharacterService implements IServiceJPA<XenoCharacterDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(XenoCharacterService.class);

    private IXenoCharacterRepository repository;

    /**
     * Default constructor of the class
     */
    public XenoCharacterService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IXenoCharacterRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to add a XenoCharacterDTO
     * @param xenoCharacterDTO the character to add
     * @return true if the character was added, false otherwise
     */
    @Override
    public boolean add(XenoCharacterDTO xenoCharacterDTO) {
        if (repository.existsById(xenoCharacterDTO.getId())){
            return false;
        }
        repository.save(IXenoCharacterMapper.INSTANCE.toEntity(xenoCharacterDTO));
        return true;
    }

    /**
     * Updates a XenoCharacterDTO in the repository.
     *
     * @param id the identifier of the XenoCharacterDTO to be updated
     * @param xenoCharacterDTO the XenoCharacterDTO object containing the updated data
     * @return true if the XenoCharacterDTO was successfully updated, false if the XenoCharacterDTO is null
     * @throws Exception if the XenoCharacterDTO with the given id is not found
     */
    @Override
    public boolean update(int id, XenoCharacterDTO xenoCharacterDTO) throws Exception {
        try {
            XenoCharacter toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            XenoCharacter aux = IXenoCharacterMapper.INSTANCE.toEntity(xenoCharacterDTO);
            toUpdate.setName(aux.getName());
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    /**
     * Gets all the XenoCharacterDTO from the repository.
     *
     * @return a list of XenoCharacterDTO
     */
    @Override
    public List<XenoCharacterDTO> getAll() {
        List<XenoCharacter> xenoCharacters = repository.findAll();
        List<XenoCharacterDTO> xenoCharacterDTOS = new ArrayList<>();
        for (XenoCharacter xenoCharacter : xenoCharacters){
            xenoCharacterDTOS.add(IXenoCharacterMapper.INSTANCE.toDTO(xenoCharacter));
        }
        return xenoCharacterDTOS;
    }

    /**
     * Gets a XenoCharacterDTO from the repository by its id.
     *
     * @param id the identifier of the XenoCharacterDTO to be retrieved
     * @return the XenoCharacterDTO with the given id, or null if no XenoCharacterDTO with the given id exists
     */
    @Override
    public XenoCharacterDTO getById(int id) {
        return IXenoCharacterMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a XenoCharacterDTO from the repository by its id.
     *
     * @param id the identifier of the XenoCharacterDTO to be deleted
     * @return true if the XenoCharacterDTO was successfully deleted, false if the XenoCharacterDTO with the given id is not found
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
