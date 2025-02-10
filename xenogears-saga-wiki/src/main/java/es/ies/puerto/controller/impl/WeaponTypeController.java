package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.WeaponTypeDTO;
import es.ies.puerto.service.impl.WeaponTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents a REST controller for WeaponTypeDTO objects.
 * It provides methods for adding, updating, retrieving, and deleting WeaponTypeDTO objects.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v2/weapontypes")
@CrossOrigin
public class WeaponTypeController implements IController<WeaponTypeDTO> {

    /**
     * Service instance for WeaponType operations.
     */
    private WeaponTypeService service;

    /**
     * Default constructor of the class.
     */
    public WeaponTypeController() {
    }

    /**
     * Constructor of the class.
     *
     * @param service WeaponTypeService instance to be used.
     */
    public WeaponTypeController(WeaponTypeService service) {
        this.service = service;
    }

    /**
     * Setter of the service.
     *
     * @param service WeaponTypeService instance to be used.
     */
    @Autowired
    public void setService(WeaponTypeService service) {
        this.service = service;
    }

    /**
     * Adds a new WeaponType.
     *
     * @param weaponTypeDTO WeaponTypeDTO object to be added.
     * @return ResponseEntity with HTTP status CREATED (201) if successful.
     */
    @Override
    @PostMapping
    public ResponseEntity<WeaponTypeDTO> add(@RequestBody WeaponTypeDTO weaponTypeDTO) {
        service.add(weaponTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing WeaponType.
     *
     * @param id        ID of the WeaponType to be updated.
     * @param weaponTypeDTO WeaponTypeDTO object with updated values.
     * @return ResponseEntity with HTTP status OK (200) if successful.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") int id, @RequestBody WeaponTypeDTO weaponTypeDTO) {
        try {
            service.update(id, weaponTypeDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of all WeaponTypes.
     *
     * @return ResponseEntity with HTTP status OK (200) containing a list of WeaponTypeDTO objects.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<WeaponTypeDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a WeaponType by ID.
     *
     * @param id ID of the WeaponType to be retrieved.
     * @return ResponseEntity with HTTP status OK (200) containing a WeaponTypeDTO object.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<WeaponTypeDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a WeaponType by ID.
     *
     * @param id ID of the WeaponType to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT (204) if successful.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}