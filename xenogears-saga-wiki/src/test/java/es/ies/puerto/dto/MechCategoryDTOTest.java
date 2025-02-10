package es.ies.puerto.dto;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class MechCategoryDTOTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    MechCategoryDTO mechCategory;

    @BeforeEach
    public void beforeEach(){
        mechCategory = new MechCategoryDTO();
        mechCategory.setId(USER_ID);
        mechCategory.setName(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, mechCategory.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, mechCategory.getName(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(mechCategory.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(mechCategory.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        MechCategoryDTO equals = new MechCategoryDTO(USER_ID);
        MechCategoryDTO differentId = new MechCategoryDTO(2);
        String str = "str";
        MechCategoryDTO nullObject = null;

        Assertions.assertEquals(mechCategory, equals, MESSAGE_ERROR);
        Assertions.assertEquals(mechCategory, mechCategory, MESSAGE_ERROR);
        Assertions.assertEquals(mechCategory.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(differentId, equals, MESSAGE_ERROR);
        Assertions.assertNotEquals(mechCategory, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(mechCategory, str, MESSAGE_ERROR);
    }
}
