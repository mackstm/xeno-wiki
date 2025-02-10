package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.service.impl.WeaponService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents a REST controller for WeaponDTO objects.
 * It provides methods for adding, updating, retrieving, and deleting WeaponDTO objects.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v2/weapons")
@CrossOrigin
public class WeaponController implements IController<WeaponDTO> {

    /**
     * Service instance for Weapon operations.
     */
    private WeaponService service;

    /**
     * Default constructor of the class.
     */
    public WeaponController() {
    }

    /**
     * Constructor of the class.
     *
     * @param service WeaponService instance to be used.
     */
    public WeaponController(WeaponService service) {
        this.service = service;
    }

    /**
     * Setter of the service.
     *
     * @param service WeaponService instance to be used.
     */
    @Autowired
    public void setWeaponService(WeaponService service) {
        this.service = service;
    }

    /**
     * Adds a new Weapon.
     *
     * @param weaponDTO WeaponDTO object to be added.
     * @return ResponseEntity with HTTP status CREATED (201) if successful.
     */
    @Override
    @PostMapping
    public ResponseEntity<WeaponDTO> add(@RequestBody WeaponDTO weaponDTO) {
        service.add(weaponDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing Weapon.
     *
     * @param id        ID of the Weapon to be updated.
     * @param weaponDTO WeaponDTO object with updated values.
     * @return ResponseEntity with HTTP status OK (200) if successful.
     */
    @Override
    @PutMapping
    public ResponseEntity<String> update(@PathVariable(value = "id") int id, @RequestBody WeaponDTO weaponDTO) {
        try {
            service.update(id, weaponDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of all Weapons.
     *
     * @return ResponseEntity with HTTP status OK (200) containing a list of WeaponDTO objects.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<WeaponDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a Weapon by ID.
     *
     * @param id ID of the Weapon to be retrieved.
     * @return ResponseEntity with HTTP status OK (200) containing a WeaponDTO object.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<WeaponDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a Weapon by ID.
     *
     * @param id ID of the Weapon to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT (204) if successful.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}