package es.ies.puerto.model.entities;

import es.ies.puerto.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeaponTest extends TestUtilities {
    public static final int USER_ID = 1;
    public static final String NAME = "nameTest";

    public static final XenoCharacter XENO_CHARACTER = new XenoCharacter();

    public static final WeaponType WEAPON_TYPE = new WeaponType();
    
    Weapon weapon;

    @BeforeEach
    public void beforeEach(){
        weapon = new Weapon();
        weapon.setId(USER_ID);
        weapon.setName(NAME);
        weapon.setXenoCharacter(XENO_CHARACTER);
        weapon.setWeaponType(WEAPON_TYPE);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(USER_ID, weapon.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, weapon.getName(), MESSAGE_ERROR);
        Assertions.assertEquals(XENO_CHARACTER, weapon.getXenoCharacter(), MESSAGE_ERROR);
        Assertions.assertEquals(WEAPON_TYPE, weapon.getWeaponType(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(weapon.toString().contains(String.valueOf(USER_ID)), MESSAGE_ERROR);
        Assertions.assertTrue(weapon.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        Weapon equals = new Weapon(USER_ID);
        Weapon completelyDifferent = new Weapon(3);
        String str = "str";
        Weapon nullObject = null;

        Assertions.assertEquals(weapon, equals, MESSAGE_ERROR);
        Assertions.assertEquals(weapon, weapon, MESSAGE_ERROR);
        Assertions.assertEquals(weapon.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(weapon, completelyDifferent, MESSAGE_ERROR);
        Assertions.assertNotEquals(weapon, nullObject, MESSAGE_ERROR);
        Assertions.assertNotEquals(weapon, str, MESSAGE_ERROR);
    }
}