package es.ies.puerto.dto;

import es.ies.puerto.model.entities.Mech;
import es.ies.puerto.model.entities.MechCategory;
import es.ies.puerto.model.entities.XenoCharacter;
import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MechDTOTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final XenoCharacterDTO PILOT = new XenoCharacterDTO();
    public static final MechCategoryDTO MECH_CATEGORY = new MechCategoryDTO();
    MechDTO mech;

    @BeforeEach
    public void beforeEach(){
        mech = new MechDTO();
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
        MechDTO equals = new MechDTO(USER_ID);
        MechDTO completelyDifferent = new MechDTO(3);
        String str = "str";
        MechDTO nullObject = null;

        Assertions.assertEquals(mech, equals, MESSAGE_ERROR);
        Assertions.assertEquals(mech, mech, MESSAGE_ERROR);
        Assertions.assertEquals(mech.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, completelyDifferent, MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(mech, str, MESSAGE_ERROR);
    }
}
