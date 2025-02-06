package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.MechDTO;
import es.ies.puerto.service.impl.MechService;
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
@RequestMapping("/api/v1/mechs")
public class MechController implements IController<MechDTO> {
    /**
     * Properties
     */
    private MechService service;

    /**
     * Default constructor of the class
     */
    public MechController() {
    }

    /**
     * Constructor of the class
     * @param service
     */
    public MechController(MechService service) {
        this.service = service;
    }

    /**
     * Setter of the service
     * @param service
     */
    @Autowired
    public void setMgsCharacterService(MechService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<MechDTO> add(@RequestBody MechDTO mechDTO) {
        service.add(mechDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

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

    @GetMapping
    @Override
    public ResponseEntity<List<MechDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MechDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
