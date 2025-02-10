package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for WeaponDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class WeaponSoap implements IServiceSoap<WeaponDTO> {

    /**
     * The service instance used to interact with the data storage.
     */
    IServiceJPA<WeaponDTO> service;

    /**
     * Sets the WeaponRepository instance to be used by this WeaponSoap service.
     *
     * @param service the IServiceJPA instance to be used
     */
    @Autowired
    public void setWeaponRepository(IServiceJPA<WeaponDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all WeaponDTO objects.
     *
     * @return a list of WeaponDTO objects
     */
    @Override
    public List<WeaponDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a WeaponDTO object by its ID.
     *
     * @param id the ID of the WeaponDTO object to retrieve
     * @return the WeaponDTO object with the specified ID, or throws a WebServiceException if not found
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public WeaponDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new WeaponDTO object to the data storage.
     *
     * @param weaponDTO the WeaponDTO object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(WeaponDTO weaponDTO) {
        return service.add(weaponDTO);
    }

    /**
     * Updates an existing WeaponDTO object in the data storage.
     *
     * @param weaponDTO the WeaponDTO object to update
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(WeaponDTO weaponDTO) {
        try {
            return service.update(weaponDTO.getId(), weaponDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a WeaponDTO object from the data storage by its ID.
     *
     * @param id the ID of the WeaponDTO object to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
