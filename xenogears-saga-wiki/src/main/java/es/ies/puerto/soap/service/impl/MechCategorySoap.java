package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.MechCategoryDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class MechCategorySoap implements IServiceSoap<MechCategoryDTO> {

    IServiceJPA<MechCategoryDTO> service;

    @Autowired
    public void setMechCategoryRepository(IServiceJPA<MechCategoryDTO> service) {
        this.service = service;
    }

    @Override
    public List<MechCategoryDTO> getAll() {
        return service.getAll();
    }

    @Override
    public MechCategoryDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(MechCategoryDTO mechCategoryDTO) {
        return service.add(mechCategoryDTO);
    }

    @Override
    public boolean update(MechCategoryDTO mechCategoryDTO) {
        try {
            return service.update(mechCategoryDTO.getId(), mechCategoryDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
