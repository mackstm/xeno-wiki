package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.WeaponTypeDTO;
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
public class WeaponTypeSoap implements IServiceSoap<WeaponTypeDTO> {

    IServiceJPA<WeaponTypeDTO> service;

    @Autowired
    public void setWeaponTypeRepository(IServiceJPA<WeaponTypeDTO> service) {
        this.service = service;
    }

    @Override
    public List<WeaponTypeDTO> getAll() {
        return service.getAll();
    }

    @Override
    public WeaponTypeDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(WeaponTypeDTO weaponTypeDTO) {
        return service.add(weaponTypeDTO);
    }

    @Override
    public boolean update(WeaponTypeDTO weaponTypeDTO) {
        try {
            return service.update(weaponTypeDTO.getId(), weaponTypeDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
