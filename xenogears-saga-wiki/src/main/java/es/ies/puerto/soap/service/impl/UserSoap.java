package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for UserDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class UserSoap implements IServiceSoap<UserDTO> {

    /**
     * The IServiceJPA instance used to interact with the UserDTO data storage.
     */
    IServiceJPA<UserDTO> service;

    /**
     * Sets the IServiceJPA instance to be used by this UserSoap service.
     *
     * @param service the IServiceJPA instance to be used
     */
    @Autowired
    public void setUserRepository(IServiceJPA<UserDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all UserDTO objects.
     *
     * @return a list of UserDTO objects
     */
    @Override
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a UserDTO object by its ID.
     *
     * @param id the ID of the UserDTO object to retrieve
     * @return the UserDTO object with the specified ID, or null if not found
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public UserDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new UserDTO object to the data storage.
     *
     * @param userDTO the UserDTO object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(UserDTO userDTO) {
        return service.add(userDTO);
    }

    /**
     * Updates an existing UserDTO object in the data storage.
     *
     * @param userDTO the UserDTO object to update
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(UserDTO userDTO) {
        try {
            return service.update(userDTO.getId(), userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a UserDTO object from the data storage by its ID.
     *
     * @param id the ID of the UserDTO object to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}