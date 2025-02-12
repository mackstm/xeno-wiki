package es.ies.puerto.service.impl;

import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.mapper.struct.IUserMapper;
import es.ies.puerto.mapper.struct.IWeaponTypeMapper;
import es.ies.puerto.model.db.dao.IUserRepository;
import es.ies.puerto.model.entities.User;
import es.ies.puerto.service.interfaces.IServiceJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mackstm
 */
@Component
@Transactional()
public class UserService implements IServiceJPA<UserDTO> {
    /**
     * Properties
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private IUserRepository repository;

    /**
     * Default constructor of the class
     */
    public UserService(){}

    /**
     * Setter of the dao
     * @param repository
     */
    @Autowired
    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to add a UserDTO to the database
     * @param userDTO the userDTO to add
     * @return true if the UserDTO was added successfully, false otherwise
     */
    @Override
    public boolean add(UserDTO userDTO) {
        if (repository.existsById(userDTO.getId())){
            return false;
        }
        repository.save(IUserMapper.INSTANCE.toEntity(userDTO));
        return true;
    }

/**
 * Updates an existing User entity in the database with the given UserDTO.
 *
 * @param id the identifier of the User to be updated
 * @param userDTO the data transfer object containing updated data
 * @return true if the update was successful, false otherwise
 * @throws Exception if the User with the given id does not exist
 */
    @Override
    public boolean update(int id, UserDTO userDTO) throws Exception {
        try {
            User toUpdate = repository.findById(id).orElseThrow(() ->
                    new Exception("Element not found for this id :: " + id));

            User aux = IUserMapper.INSTANCE.toEntity(userDTO);
            if (aux.getUsername() != null) {
                toUpdate.setUsername(aux.getUsername());
            }
            if (aux.getEmail() != null) {
                toUpdate.setEmail(aux.getEmail());
            }
            if (aux.getRole() != null) {
                toUpdate.setRole(aux.getRole());
            }
            repository.save(toUpdate);
            return true;

        } catch (Exception e){
            return false;
        }
    }


    /**
     * Returns a list of all UserDTO objects in the database.
     *
     * @return a list of UserDTO objects
     */
    @Override
    public List<UserDTO> getAll() {
        List<User> users = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users){
            userDTOS.add(IUserMapper.INSTANCE.toDTO(user));
        }
        return userDTOS;
    }

/**
 * Retrieves a UserDTO from the database by its identifier.
 *
 * @param id the identifier of the UserDTO to be retrieved
 * @return the UserDTO object with the given id, or null if no such User exists
 */
    @Override
    public UserDTO getById(int id) {
        return IUserMapper.INSTANCE.toDTO(repository.findById(id).orElse(null));
    }

    /**
     * Deletes a UserDTO object from the database.
     *
     * @param id the identifier of the UserDTO to be deleted
     * @return true if the UserDTO was deleted successfully, false otherwise
     */
    @Override
    public boolean delete(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
