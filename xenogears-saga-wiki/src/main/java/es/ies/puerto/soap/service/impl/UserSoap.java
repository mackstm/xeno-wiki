package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class UserSoap implements IServiceSoap<UserDTO> {

    IServiceJPA<UserDTO> service;

    @Autowired
    public void setUserRepository(IServiceJPA<UserDTO> service) {
        this.service = service;
    }

    @Override
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @Override
    public UserDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(UserDTO userDTO) {
        return service.add(userDTO);
    }

    @Override
    public boolean update(UserDTO userDTO) {
        try {
            return service.update(userDTO.getId(), userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
