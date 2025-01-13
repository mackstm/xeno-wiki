package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class RoleSoap implements IServiceSoap<RoleDTO> {

    IServiceJPA<RoleDTO> service;

    @Autowired
    public void setRoleRepository(IServiceJPA<RoleDTO> service) {
        this.service = service;
    }

    @Override
    public List<RoleDTO> getAll() {
        return service.getAll();
    }

    @Override
    public RoleDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(RoleDTO roleDTO) {
        return service.add(roleDTO);
    }

    @Override
    public boolean update(RoleDTO roleDTO) {
        try {
            return service.update(roleDTO.getId(), roleDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
