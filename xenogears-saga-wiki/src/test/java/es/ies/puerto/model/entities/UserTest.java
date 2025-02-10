package es.ies.puerto.model.entities;

import es.ies.puerto.model.entities.Role;
import es.ies.puerto.model.entities.User;
import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "password";
    public static final Role ROLE = new Role(1, "Admin");
    User user;

    @BeforeEach
    public void beforeEach(){
        user = new User();
        user.setId(USER_ID);
        user.setUsername(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, user.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, user.getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(EMAIL, user.getEmail(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, user.getPassword(), MESSAGE_ERROR);
        Assertions.assertEquals(ROLE, user.getRole(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(user.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(user.toString().contains(NAME), MESSAGE_ERROR);
        Assertions.assertTrue(user.toString().contains(EMAIL), MESSAGE_ERROR);
        Assertions.assertFalse(user.toString().contains(PASSWORD), MESSAGE_ERROR);
        Assertions.assertTrue(user.toString().contains(ROLE.getName()), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        User equals = new User(USER_ID, EMAIL);
        User differentId = new User(2, EMAIL);
        User completelyDifferent = new User(3, "another@gmail.com");
        String str = "str";
        User nullObject = null;

        Assertions.assertEquals(user, equals, MESSAGE_ERROR);
        Assertions.assertEquals(user, user, MESSAGE_ERROR);
        Assertions.assertEquals(user.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(user, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(user, completelyDifferent, MESSAGE_ERROR);
        Assertions.assertNotEquals(user, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(user, str, MESSAGE_ERROR);
    }
}