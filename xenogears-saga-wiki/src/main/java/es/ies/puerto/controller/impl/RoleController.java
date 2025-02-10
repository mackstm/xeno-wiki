package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.service.impl.RoleService;
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
@RequestMapping("/api/v3/roles")
@CrossOrigin
public class RoleController implements IController<RoleDTO> {

    /**
     * Properties
     */

    /**
     * Service instance for Role operations.
     */
    private RoleService service;

    /**
     * Default constructor of the class.
     */
    public RoleController() {
    }

    /**
     * Constructor of the class.
     *
     * @param service RoleService instance to be used.
     */
    public RoleController(RoleService service) {
        this.service = service;
    }

    /**
     * Setter of the service.
     *
     * @param service RoleService instance to be used.
     */
    @Autowired
    public void setRoleService(RoleService service) {
        this.service = service;
    }

    /**
     * Adds a new Role.
     *
     * @param dto RoleDTO instance to be added.
     * @return ResponseEntity with HTTP status CREATED.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> add(@RequestBody RoleDTO dto) {
        service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing Role.
     *
     * @param id  ID of the Role to be updated.
     * @param dto RoleDTO instance with updated values.
     * @return ResponseEntity with HTTP status OK.
     */
    @Override
    @PutMapping
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody RoleDTO dto) {
        try {
            service.update(id, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all Roles.
     *
     * @return ResponseEntity with a list of RoleDTO instances.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a Role by ID.
     *
     * @param id ID of the Role to be retrieved.
     * @return ResponseEntity with a RoleDTO instance.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a Role by ID.
     *
     * @param id ID of the Role to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT if deleted successfully, or BAD_REQUEST if deletion fails.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (service.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.badRequest().body("Can't delete admin");
        }
    }
}