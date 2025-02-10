package es.ies.puerto.model.entities;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MechTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final XenoCharacter PILOT = new XenoCharacter();
    public static final MechCategory MECH_CATEGORY = new MechCategory();
    Mech mech;

    @BeforeEach
    public void beforeEach(){
        mech = new Mech();
        mech.setId(USER_ID);
        mech.setName(NAME);
        mech.setMechCategory(MECH_CATEGORY);
        mech.setXenoCharacter(PILOT);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, mech.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, mech.getName(), MESSAGE_ERROR);
        Assertions.assertEquals(PILOT, mech.getXenoCharacter(), MESSAGE_ERROR);
        Assertions.assertEquals(MECH_CATEGORY, mech.getMechCategory(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(mech.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(mech.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        Mech equals = new Mech(USER_ID);
        Mech completelyDifferent = new Mech(3);
        String str = "str";
        Mech nullObject = null;

        Assertions.assertEquals(mech, equals, MESSAGE_ERROR);
        Assertions.assertEquals(mech, mech, MESSAGE_ERROR);
        Assertions.assertEquals(mech.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, completelyDifferent, MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, str, MESSAGE_ERROR);
    }
}