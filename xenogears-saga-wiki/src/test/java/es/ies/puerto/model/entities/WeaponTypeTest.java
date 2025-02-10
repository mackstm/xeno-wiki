package es.ies.puerto.model.entities;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class WeaponTypeTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";
    public static final Set<Weapon> WEAPONS = new HashSet<>();
    WeaponType weaponType;

    @BeforeEach
    public void beforeEach(){
        weaponType = new WeaponType();
        weaponType.setId(USER_ID);
        weaponType.setName(NAME);
        weaponType.setWeapons(WEAPONS);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, weaponType.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, weaponType.getName(), MESSAGE_ERROR);
        Assertions.assertEquals(WEAPONS, weaponType.getWeapons(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(weaponType.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(weaponType.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        WeaponType equals = new WeaponType(USER_ID);
        WeaponType differentId = new WeaponType(2);
        String str = "str";
        WeaponType nullObject = null;

        Assertions.assertEquals(weaponType, equals, MESSAGE_ERROR);
        Assertions.assertEquals(weaponType, weaponType, MESSAGE_ERROR);
        Assertions.assertEquals(weaponType.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(weaponType, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(weaponType, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(weaponType, str, MESSAGE_ERROR);
    }
}