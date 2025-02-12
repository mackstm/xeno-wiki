package es.ies.puerto.service;

import es.ies.puerto.dto.RoleDTO;
import es.ies.puerto.dto.UserDTO;
import es.ies.puerto.model.db.dao.IUserRepository;
import es.ies.puerto.model.entities.User;
import es.ies.puerto.service.impl.UserService;
import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest extends TestUtilities {
    @Mock
    IUserRepository repositoryMock;

    @InjectMocks
    UserService service;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new UserService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(new User(1));
        list.add(new User(2));
        list.add(new User(3));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.getAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.getById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(Optional.of(new User()));
        Assertions.assertNotNull(service.getById(1), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        Mockito.when(repositoryMock.existsById(1)).thenReturn(false);
        Mockito.when(repositoryMock.save(Mockito.any(User.class))).thenReturn(new User());
        Assertions.assertTrue(service.add(new UserDTO(1)), MESSAGE_ERROR);
    }

    @Test
    void addDupeTest() {
        Mockito.when(repositoryMock.existsById(1)).thenReturn(true);
        Assertions.assertFalse(service.add(new UserDTO(1)), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Mockito.when(repositoryMock.existsById(1)).thenReturn(true);
        Assertions.assertFalse(service.add(new UserDTO(1)), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Assertions.assertFalse(service.update(1, new UserDTO(1)), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenReturn(Optional.of(new User()));
        Assertions.assertTrue(service.update(1,new UserDTO(1)), MESSAGE_ERROR);
    }

    @Test
    void updateWithDataTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenReturn(Optional.of(new User()));
        Assertions.assertTrue(service.update(1,new UserDTO(1, "", "", new RoleDTO())), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertFalse(service.update(0, null), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        Mockito.when(repositoryMock.existsById(1)).thenReturn(true);
        Assertions.assertTrue(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteNonExistentTest() {
        Mockito.when(repositoryMock.existsById(1)).thenReturn(false);
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }
}
