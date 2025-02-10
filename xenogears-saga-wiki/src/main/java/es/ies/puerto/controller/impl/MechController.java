package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.service.impl.MechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents a REST controller for MechDTO objects.
 * It provides methods for adding, updating, retrieving, and deleting MechDTO objects.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v2/mechs")
@CrossOrigin
public class MechController implements IController<MechDTO> {

    /**
     * The service object used to interact with the MechDTO objects.
     */
    private MechService service;

    /**
     * Default constructor of the class.
     */
    public MechController() {
    }

    /**
     * Constructor of the class.
     *
     * @param service the MechService object to be used.
     */
    public MechController(MechService service) {
        this.service = service;
    }

    /**
     * Setter method for the MechService object.
     *
     * @param service the MechService object to be set.
     */
    @Autowired
    public void setMgsCharacterService(MechService service) {
        this.service = service;
    }

    /**
     * Adds a new MechDTO object.
     *
     * @param mechDTO the MechDTO object to be added.
     * @return a ResponseEntity with the HTTP status CREATED.
     */
    @Override
    @PostMapping
    public ResponseEntity<MechDTO> add(@RequestBody MechDTO mechDTO) {
        service.add(mechDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing MechDTO object.
     *
     * @param id the ID of the MechDTO object to be updated.
     * @param mechDTO the updated MechDTO object.
     * @return a ResponseEntity with the HTTP status OK.
     */
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody MechDTO mechDTO) {
        try {
            service.update(id, mechDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all MechDTO objects.
     *
     * @return a ResponseEntity with a list of MechDTO objects and the HTTP status OK.
     */
    @GetMapping
    @Override
    public ResponseEntity<List<MechDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a MechDTO object by its ID.
     *
     * @param id the ID of the MechDTO object to be retrieved.
     * @return a ResponseEntity with the MechDTO object and the HTTP status OK.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MechDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a MechDTO object by its ID.
     *
     * @param id the ID of the MechDTO object to be deleted.
     * @return a ResponseEntity with the HTTP status NO_CONTENT.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}