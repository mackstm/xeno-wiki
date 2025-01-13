package es.ies.puerto.service.impl;

import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.mapper.struct.IXenoCharacterMapper;
import es.ies.puerto.model.db.dao.IDaoXenoCharacter;
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

    private IDaoXenoCharacter repository;

    /**
     * Default constructor of the class
     */
    public XenoCharacterService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setiDaoMGSCharacter(IDaoXenoCharacter repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(XenoCharacterDTO xenoCharacterDTO) {
        if (xenoCharacterDTO == null){
            return false;
        }
        repository.save(IXenoCharacterMapper.INSTANCE.toEntity(xenoCharacterDTO));
        return true;
    }

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


    @Override
    public List<XenoCharacterDTO> getAll() {
        List<XenoCharacter> xenoCharacters = repository.findAll();
        List<XenoCharacterDTO> xenoCharacterDTOS = new ArrayList<>();
        for (XenoCharacter xenoCharacter : xenoCharacters){
            xenoCharacterDTOS.add(IXenoCharacterMapper.INSTANCE.toDTO(xenoCharacter));
        }
        return xenoCharacterDTOS;
    }

    @Override
    public XenoCharacterDTO getById(int id) {
        Integer auxId = id;
        if (!repository.existsById(auxId)) {
            return null;
        }

        XenoCharacterDTO result = null;

        List<XenoCharacterDTO> list = getAll();

        for (XenoCharacterDTO xenoCharacterDTO : list){
            if (xenoCharacterDTO.getId() == id){
                result = xenoCharacterDTO;
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
