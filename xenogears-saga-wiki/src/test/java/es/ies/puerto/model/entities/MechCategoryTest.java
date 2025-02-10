package es.ies.puerto.model.entities;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MechCategoryTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final Set<Mech> MECHS = new HashSet<>();
    MechCategory mechCategory;

    @BeforeEach
    public void beforeEach(){
        mechCategory = new MechCategory();
        mechCategory.setId(USER_ID);
        mechCategory.setName(NAME);
        mechCategory.setMechs(MECHS);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, mechCategory.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, mechCategory.getName(), MESSAGE_ERROR);
        Assertions.assertEquals(MECHS, mechCategory.getMechs(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(mechCategory.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(mechCategory.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        MechCategory equals = new MechCategory(USER_ID);
        MechCategory differentId = new MechCategory(2);
        String str = "str";
        MechCategory nullObject = null;

        Assertions.assertEquals(mechCategory, equals, MESSAGE_ERROR);
        Assertions.assertEquals(mechCategory, mechCategory, MESSAGE_ERROR);
        Assertions.assertEquals(mechCategory.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(differentId, equals, MESSAGE_ERROR);
        Assertions.assertNotEquals(mechCategory, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(mechCategory, str, MESSAGE_ERROR);
    }
}