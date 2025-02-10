package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.MechCategoryDTO;
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
public class MechCategorySoap implements IServiceSoap<MechCategoryDTO> {

    IServiceJPA<MechCategoryDTO> service;

    /**
     * Sets the service of the MechCategorySoap
     * @param service to set
     */
    @Autowired
    public void setMechCategoryRepository(IServiceJPA<MechCategoryDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves all the MechCategoryDTO objects from the database
     *
     * @return a List of MechCategoryDTO objects
     */
    @Override
    public List<MechCategoryDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a MechCategoryDTO by its id.
     *
     * @param id the identifier of the MechCategoryDTO to retrieve
     * @return the MechCategoryDTO object if it exists, otherwise throws a WebServiceException
     * @throws WebServiceException if an error occurs during retrieval
     */
    @Override
    public MechCategoryDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a MechCategoryDTO to the database.
     *
     * @param mechCategoryDTO the MechCategoryDTO object to add
     * @return true if the MechCategoryDTO was added successfully, false otherwise
     */
    @Override
    public boolean add(MechCategoryDTO mechCategoryDTO) {
        return service.add(mechCategoryDTO);
    }

    /**
     * Updates an existing MechCategory entity in the database with the given MechCategoryDTO.
     *
     * @param mechCategoryDTO the MechCategoryDTO object containing updated data
     * @return true if the update was successful, false otherwise
     * @throws RuntimeException if an error occurs during the update
     */
    @Override
    public boolean update(MechCategoryDTO mechCategoryDTO) {
        try {
            return service.update(mechCategoryDTO.getId(), mechCategoryDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Deletes a MechCategory entity from the database.
     *
     * @param id the identifier of the MechCategory entity to delete
     * @return true if the MechCategory entity was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}
