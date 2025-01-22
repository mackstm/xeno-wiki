package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.WeaponDTO;
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
public class WeaponSoap implements IServiceSoap<WeaponDTO> {

    IServiceJPA<WeaponDTO> service;

    @Autowired
    public void setWeaponRepository(IServiceJPA<WeaponDTO> service) {
        this.service = service;
    }

    @Override
    public List<WeaponDTO> getAll() {
        return service.getAll();
    }

    @Override
    public WeaponDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    @Override
    public boolean add(WeaponDTO weaponDTO) {
        return service.add(weaponDTO);
    }

    @Override
    public boolean update(WeaponDTO weaponDTO) {
        try {
            return service.update(weaponDTO.getId(), weaponDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
