package es.ies.puerto.dto;

import es.ies.puerto.model.entities.Role;
import es.ies.puerto.model.entities.User;
import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserLoginTest extends TestUtilities {
    public static final String NAME = "nameTest";
    public static final String PASSWORD = "password";
    UserLoginDTO user;

    @BeforeEach
    public void beforeEach(){
        user = new UserLoginDTO(NAME, PASSWORD);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, user.username(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, user.password(), MESSAGE_ERROR);
    }
}
