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

@RestController
@RequestMapping("/v1/mechcategories")
public class MechCategoryController implements IController<MechCategoryDTO> {
    /**
     * Properties
     */
    private MechCategoryService service;

    /**
     * Default constructor of the class
     */
    public MechCategoryController() {
    }

    /**
     * Constructor of the class
     * @param service
     */
    public MechCategoryController(MechCategoryService service) {
        this.service = service;
    }

    /**
     * Setter of the service
     * @param service
     */
    @Autowired
    public void setMgsCharacterService(MechCategoryService service) {
        this.service = service;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity add(@RequestBody MechCategoryDTO mechCategoryDTO) {
        service.add(mechCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") int id, @RequestBody MechCategoryDTO mechCategoryDTO) {
        try {
            service.update(id, mechCategoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @GetMapping("/")
    public ResponseEntity<List<MechCategoryDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MechCategoryDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
