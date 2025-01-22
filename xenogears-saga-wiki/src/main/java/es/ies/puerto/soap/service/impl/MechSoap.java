package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class MechSoap implements IServiceSoap<MechDTO> {

    IServiceJPA<MechDTO> service;

    @Autowired
    public void setMechRepository(IServiceJPA<MechDTO> service) {
        this.service = service;
    }

    @Override
    public List<MechDTO> getAll() {
        return service.getAll();
    }

    @Override
    public MechDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(MechDTO mechDTO) {
        return service.add(mechDTO);
    }

    @Override
    public boolean update(MechDTO mechDTO) {
        try {
            return service.update(mechDTO.getId(), mechDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
