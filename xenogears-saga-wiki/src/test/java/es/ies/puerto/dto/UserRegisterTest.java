package es.ies.puerto.dto;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRegisterTest extends TestUtilities {
    public static final String NAME = "nameTest";

    public static final String EMAIL = "example@example.com";
    public static final String PASSWORD = "password";
    UserRegisterDTO user;

    @BeforeEach
    public void beforeEach(){
        user = new UserRegisterDTO(NAME, PASSWORD, EMAIL);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, user.username(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, user.password(), MESSAGE_ERROR);
        Assertions.assertEquals(EMAIL, user.email(), MESSAGE_ERROR);
    }
}
