package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for RoleDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class RoleSoap implements IServiceSoap<RoleDTO> {

    /**
     * The IServiceJPA instance used to interact with the RoleDTO data storage.
     */
    private IServiceJPA<RoleDTO> service;

    /**
     * Sets the RoleRepository instance to be used by this RoleSoap service.
     *
     * @param service the IServiceJPA instance to be used
     */
    @Autowired
    public void setRoleRepository(IServiceJPA<RoleDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all RoleDTO objects.
     *
     * @return a list of RoleDTO objects
     */
    @Override
    public List<RoleDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a RoleDTO object by its ID.
     *
     * @param id the ID of the RoleDTO object to retrieve
     * @return the RoleDTO object with the specified ID, or null if not found
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public RoleDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new RoleDTO object to the data storage.
     *
     * @param roleDTO the RoleDTO object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(RoleDTO roleDTO) {
        return service.add(roleDTO);
    }

    /**
     * Updates an existing RoleDTO object in the data storage.
     *
     * @param roleDTO the RoleDTO object to update
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(RoleDTO roleDTO) {
        try {
            return service.update(roleDTO.getId(), roleDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a RoleDTO object from the data storage by its ID.
     *
     * @param id the ID of the RoleDTO object to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}