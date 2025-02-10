package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.MechCategoryDTO;
import es.ies.puerto.service.impl.MechCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents a REST controller for MechCategoryDTO objects.
 * It provides methods for adding, updating, retrieving, and deleting MechCategoryDTO objects.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v2/mechcategories")
@CrossOrigin
public class MechCategoryController implements IController<MechCategoryDTO> {

    /**
     * The service object used to interact with the MechCategoryDTO objects.
     */
    private MechCategoryService service;

    /**
     * Default constructor of the class.
     * Initializes the controller with no service.
     */
    public MechCategoryController() {
    }

    /**
     * Constructor of the class.
     * Initializes the controller with the given service.
     *
     * @param service the MechCategoryService object to be used.
     */
    public MechCategoryController(MechCategoryService service) {
        this.service = service;
    }

    /**
     * Setter of the service.
     * Sets the MechCategoryService object to be used by the controller.
     *
     * @param service the MechCategoryService object to be set.
     */
    @Autowired
    public void setMgsCharacterService(MechCategoryService service) {
        this.service = service;
    }

    /**
     * Adds a new MechCategoryDTO object.
     * Creates a new MechCategoryDTO object and adds it to the database.
     *
     * @param mechCategoryDTO the MechCategoryDTO object to be added.
     * @return a ResponseEntity with the HTTP status CREATED.
     */
    @Override
    @PostMapping
    public ResponseEntity<MechCategoryDTO> add(@RequestBody MechCategoryDTO mechCategoryDTO) {
        service.add(mechCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing MechCategoryDTO object.
     * Updates the MechCategoryDTO object with the given ID and information.
     *
     * @param id the ID of the MechCategoryDTO object to be updated.
     * @param mechCategoryDTO the updated MechCategoryDTO object.
     * @return a ResponseEntity with the HTTP status OK.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody MechCategoryDTO mechCategoryDTO) {
        try {
            service.update(id, mechCategoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all MechCategoryDTO objects.
     * Returns a list of all MechCategoryDTO objects in the database.
     *
     * @return a ResponseEntity with a list of MechCategoryDTO objects and the HTTP status OK.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<MechCategoryDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a MechCategoryDTO object by its ID.
     * Returns the MechCategoryDTO object with the given ID.
     *
     * @param id the ID of the MechCategoryDTO object to be retrieved.
     * @return a ResponseEntity with the MechCategoryDTO object and the HTTP status OK.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MechCategoryDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a MechCategoryDTO object by its ID.
     * Deletes the MechCategoryDTO object with the given ID.
     *
     * @param id the ID of the MechCategoryDTO object to be deleted.
     * @return a ResponseEntity with the HTTP status NO_CONTENT.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}