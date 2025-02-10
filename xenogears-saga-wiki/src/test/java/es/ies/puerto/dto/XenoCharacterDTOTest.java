package es.ies.puerto.dto;

import es.ies.puerto.model.entities.Mech;
import es.ies.puerto.model.entities.Weapon;
import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class XenoCharacterDTOTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final Weapon WEAPON = new Weapon();
    XenoCharacterDTO user;

    @BeforeEach
    public void beforeEach(){
        user = new XenoCharacterDTO();
        user.setId(USER_ID);
        user.setName(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, user.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, user.getName(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(user.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(user.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        XenoCharacterDTO equals = new XenoCharacterDTO(USER_ID);
        XenoCharacterDTO differentId = new XenoCharacterDTO(2);
        String str = "str";
        XenoCharacterDTO nullObject = null;

        Assertions.assertEquals(user, equals, MESSAGE_ERROR);
        Assertions.assertEquals(user, user, MESSAGE_ERROR);
        Assertions.assertEquals(user.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(user, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(user, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(user, str, MESSAGE_ERROR);
    }
}
