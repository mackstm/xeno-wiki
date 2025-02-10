package es.ies.puerto.controller.impl;

import es.ies.puerto.controller.interfaces.IController;
import es.ies.puerto.dto.XenoCharacterDTO;
import es.ies.puerto.service.impl.XenoCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for XenoCharacterDTO.
 *
 * @author mackstm
 */
@RestController
@RequestMapping("/api/v2/characters")
@CrossOrigin
public class XenoCharacterController implements IController<XenoCharacterDTO> {

    /**
     * Service for XenoCharacterDTO.
     */
    private XenoCharacterService service;

    /**
     * Default constructor.
     */
    public XenoCharacterController() {
    }

    /**
     * Constructor.
     *
     * @param service the service.
     */
    public XenoCharacterController(XenoCharacterService service) {
        this.service = service;
    }

    /**
     * Sets the service.
     *
     * @param service the service.
     */
    @Autowired
    public void setMgsCharacterService(XenoCharacterService service) {
        this.service = service;
    }

    /**
     * Adds a new XenoCharacterDTO.
     *
     * @param xenoCharacterDTO the XenoCharacterDTO.
     * @return the added XenoCharacterDTO.
     */
    @Override
    @PostMapping
    public ResponseEntity<XenoCharacterDTO> add(@RequestBody XenoCharacterDTO xenoCharacterDTO) {
        service.add(xenoCharacterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing XenoCharacterDTO.
     *
     * @param id the id.
     * @param xenoCharacterDTO the XenoCharacterDTO.
     * @return the updated XenoCharacterDTO.
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable(value = "id") int id, @RequestBody XenoCharacterDTO xenoCharacterDTO) {
        try {
            service.update(id, xenoCharacterDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets all XenoCharacterDTOs.
     *
     * @return the list of XenoCharacterDTOs.
     */
    @Override
    @GetMapping
    public ResponseEntity<List<XenoCharacterDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Gets a XenoCharacterDTO by id.
     *
     * @param id the id.
     * @return the XenoCharacterDTO.
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<XenoCharacterDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    /**
     * Deletes a XenoCharacterDTO by id.
     *
     * @param id the id.
     * @return the deleted XenoCharacterDTO.
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}