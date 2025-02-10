package es.ies.puerto.soap.service.impl;

import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.service.interfaces.IServiceJPA;
import es.ies.puerto.soap.service.interfaces.IServiceSoap;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementation of the IServiceSoap interface, providing SOAP-based services for XenoCharacterDTO objects.
 *
 * @author mackstm
 */
@WebService(endpointInterface = "es.ies.puerto.soap.service.interfaces.IServiceSoap")
public class XenoCharacterSoap implements IServiceSoap<XenoCharacterDTO> {

    /**
     * The service instance used to interact with the data repository.
     */
    IServiceJPA<XenoCharacterDTO> service;

    /**
     * Sets the XenoCharacterRepository instance to be used by this service.
     *
     * @param service the XenoCharacterRepository instance to be used
     */
    @Autowired
    public void setXenoCharacterRepository(IServiceJPA<XenoCharacterDTO> service) {
        this.service = service;
    }

    /**
     * Retrieves a list of all XenoCharacterDTO objects from the data repository.
     *
     * @return a list of XenoCharacterDTO objects
     */
    @Override
    public List<XenoCharacterDTO> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a XenoCharacterDTO object from the data repository by its ID.
     *
     * @param id the ID of the XenoCharacterDTO object to be retrieved
     * @return the XenoCharacterDTO object with the specified ID, or null if not found
     * @throws WebServiceException if an error occurs during the retrieval process
     */
    @Override
    public XenoCharacterDTO getById(int id) {
        try {
            return service.getById(id);
        } catch (Exception e) {
            throw new WebServiceException("Error", e);
        }
    }

    /**
     * Adds a new XenoCharacterDTO object to the data repository.
     *
     * @param xenoCharacterDTO the XenoCharacterDTO object to be added
     * @return true if the object was successfully added, false otherwise
     */
    @Override
    public boolean add(XenoCharacterDTO xenoCharacterDTO) {
        return service.add(xenoCharacterDTO);
    }

    /**
     * Updates an existing XenoCharacterDTO object in the data repository.
     *
     * @param xenoCharacterDTO the XenoCharacterDTO object containing the updated data
     * @return true if the object was successfully updated, false otherwise
     * @throws RuntimeException if an error occurs during the update process
     */
    @Override
    public boolean update(XenoCharacterDTO xenoCharacterDTO) {
        try {
            return service.update(xenoCharacterDTO.getId(), xenoCharacterDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a XenoCharacterDTO object from the data repository by its ID.
     *
     * @param id the ID of the XenoCharacterDTO object to be deleted
     * @return true if the object was successfully deleted, false otherwise
     */
    @Override
    public boolean delete(int id) {
        return service.delete(id);
    }
}