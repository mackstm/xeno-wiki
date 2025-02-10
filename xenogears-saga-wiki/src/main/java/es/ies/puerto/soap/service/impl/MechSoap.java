package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for MechDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class MechSoap implements IServiceSoap<MechDTO> {

    /**
     * The IServiceJPA instance used to interact with the MechDTO data storage.
     */
    IServiceJPA<MechDTO> service;

    /**
     * Sets the MechRepository instance to be used by this MechSoap service.
     *
     * @param service the IServiceJPA instance to be used
     */
    @Autowired
    public void setMechRepository(IServiceJPA<MechDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all MechDTO objects.
     *
     * @return a list of MechDTO objects
     */
    @Override
    public List<MechDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a MechDTO object by its ID.
     *
     * @param id the ID of the MechDTO object to retrieve
     * @return the MechDTO object with the specified ID, or null if not found
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public MechDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new MechDTO object to the data storage.
     *
     * @param mechDTO the MechDTO object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(MechDTO mechDTO) {
        return service.add(mechDTO);
    }

    /**
     * Updates an existing MechDTO object in the data storage.
     *
     * @param mechDTO the MechDTO object to update
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(MechDTO mechDTO) {
        try {
            return service.update(mechDTO.getId(), mechDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a MechDTO object from the data storage by its ID.
     *
     * @param id the ID of the MechDTO object to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}