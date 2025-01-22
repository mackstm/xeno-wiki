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
 * @author mackstm
 */
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
    public ResponseEntity add(@RequestBody WeaponDTO weaponDTO) {
        service.add(weaponDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") int id, @RequestBody WeaponDTO weaponDTO) {
        try {
            service.update(id, weaponDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<WeaponDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<WeaponDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
