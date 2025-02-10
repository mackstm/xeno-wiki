package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.WeaponTypeDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for WeaponTypeDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class WeaponTypeSoap implements IServiceSoap<WeaponTypeDTO> {

    /**
     * Service instance for interacting with the database.
     */
    IServiceJPA<WeaponTypeDTO> service;

    /**
     * Sets the service instance for interacting with the database.
     *
     * @param service the service instance to be set
     */
    @Autowired
    public void setWeaponTypeRepository(IServiceJPA<WeaponTypeDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves all WeaponTypeDTO objects from the database.
     *
     * @return a list of all WeaponTypeDTO objects
     */
    @Override
    public List<WeaponTypeDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a WeaponTypeDTO object by its ID.
     *
     * @param id the ID of the WeaponTypeDTO object to be retrieved
     * @return the WeaponTypeDTO object with the given ID, or null if it does not exist
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public WeaponTypeDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new WeaponTypeDTO object to the database.
     *
     * @param weaponTypeDTO the WeaponTypeDTO object to be added
     * @return true if the object was added successfully, false otherwise
     */
    @Override
    public boolean add(WeaponTypeDTO weaponTypeDTO) {
        return service.add(weaponTypeDTO);
    }

    /**
     * Updates an existing WeaponTypeDTO object in the database.
     *
     * @param weaponTypeDTO the updated WeaponTypeDTO object
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(WeaponTypeDTO weaponTypeDTO) {
        try {
            return service.update(weaponTypeDTO.getId(), weaponTypeDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a WeaponTypeDTO object from the database by its ID.
     *
     * @param id the ID of the WeaponTypeDTO object to be deleted
     * @return true if the object was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}