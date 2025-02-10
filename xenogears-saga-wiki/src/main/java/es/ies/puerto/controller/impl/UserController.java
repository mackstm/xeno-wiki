package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents a REST controller for UserDTO objects.
 * It provides methods for adding, updating, retrieving, and deleting UserDTO objects.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v3/users")
@CrossOrigin
public class UserController implements IController<UserDTO> {

    /**
     * Service instance for User operations.
     */
    private UserService service;

    /**
     * Default constructor of the class.
     */
    public UserController() {
    }

    /**
     * Constructor of the class.
     *
     * @param service UserService instance to be used.
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Setter of the service.
     *
     * @param service UserService instance to be used.
     */
    @Autowired
    public void setUserService(UserService service) {
        this.service = service;
    }

    /**
     * Adds a new User.
     *
     * @param dto UserDTO object to be added.
     * @return ResponseEntity with a 201 status code if the User is created successfully.
     */
    @Override
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<UserDTO> add(@RequestBody UserDTO dto) {
        service.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing User.
     *
     * @param id  ID of the User to be updated.
     * @param dto UserDTO object with the updated data.
     * @return ResponseEntity with a 200 status code if the User is updated successfully.
     */
    @Override
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<String> update(@PathVariable(value = "id") int id, @RequestBody UserDTO dto) {
        try {
            service.update(id, dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all Users.
     *
     * @return ResponseEntity with a list of UserDTO objects.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Retrieves a User by ID.
     *
     * @param id ID of the User to be retrieved.
     * @return ResponseEntity with a UserDTO object if found, or a 404 status code if not found.
     */
    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a User by ID.
     *
     * @param id ID of the User to be deleted.
     * @return ResponseEntity with a 200 status code if the User is deleted successfully, or a 404 status code if not found.
     */
    @Override
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}