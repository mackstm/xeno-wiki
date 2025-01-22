package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.XenoCharacterDTO;
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
public class XenoCharacterSoap implements IServiceSoap<XenoCharacterDTO> {

    IServiceJPA<XenoCharacterDTO> service;

    @Autowired
    public void setXenoCharacterRepository(IServiceJPA<XenoCharacterDTO> service) {
        this.service = service;
    }

    @Override
    public List<XenoCharacterDTO> getAll() {
        return service.getAll();
    }

    @Override
    public XenoCharacterDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(XenoCharacterDTO xenoCharacterDTO) {
        return service.add(xenoCharacterDTO);
    }

    @Override
    public boolean update(XenoCharacterDTO xenoCharacterDTO) {
        try {
            return service.update(xenoCharacterDTO.getId(), xenoCharacterDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
