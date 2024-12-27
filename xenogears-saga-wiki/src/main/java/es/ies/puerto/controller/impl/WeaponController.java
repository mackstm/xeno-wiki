package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.WeaponDTO;
import es.ies.puerto.service.impl.WeaponService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/weapons")
public class WeaponController implements IController<WeaponDTO> {
    /**
     * Properties
     */
    private WeaponService service;

    /**
     * Default constructor of the class
     */
    public WeaponController() {
    }

    /**
     * Constructor of the class
     * @param service
     */
    public WeaponController(WeaponService service) {
        this.service = service;
    }

    /**
     * Setter of the service
     * @param service
     */
    @Autowired
    public void setMgsCharacterService(WeaponService service) {
        this.service = service;
    }

    @Override
    @PostMapping("/")
    @Operation(summary = "Insert character")
    public ResponseEntity add(WeaponDTO weaponDTO) {
        service.add(weaponDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update character")
    @Override
    public ResponseEntity update(@PathVariable(value = "id") int id, @Valid @RequestBody WeaponDTO weaponDTO) {
        try {
            service.update(id, weaponDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all characters")
    @Override
    public ResponseEntity<List<WeaponDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get character by ID")
    public ResponseEntity<WeaponDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete character")
    public ResponseEntity delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
