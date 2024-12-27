package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.service.impl.XenoCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/characters")
public class XenoCharacterController implements IController<XenoCharacterDTO> {
    /**
     * Properties
     */
    private XenoCharacterService service;

    /**
     * Default constructor of the class
     */
    public XenoCharacterController() {
    }

    /**
     * Constructor of the class
     * @param service
     */
    public XenoCharacterController(XenoCharacterService service) {
        this.service = service;
    }

    /**
     * Setter of the service
     * @param service
     */
    @Autowired
    public void setMgsCharacterService(XenoCharacterService service) {
        this.service = service;
    }

    @Override
    @PostMapping("/")
    @Operation(summary = "Insert character")
    public ResponseEntity add(XenoCharacterDTO xenoCharacterDTO) {
        service.add(xenoCharacterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update character")
    @Override
    public ResponseEntity update(@PathVariable(value = "id") int id, @Valid @RequestBody XenoCharacterDTO xenoCharacterDTO) {
        try {
            service.update(id, xenoCharacterDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Get all characters")
    @Override
    public ResponseEntity<List<XenoCharacterDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get character by ID")
    public ResponseEntity<XenoCharacterDTO> getById(@PathVariable int id) {
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
