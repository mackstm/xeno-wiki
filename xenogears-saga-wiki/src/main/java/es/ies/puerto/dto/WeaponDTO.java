package es.ies.puerto.dto;


import java.util.Objects;

public class WeaponDTO {
    /**
     * Properties
     */
    private int id;
    private String name;
    private XenoCharacterDTO xenoCharacter;
    private WeaponTypeDTO weaponType;

    /**
     * Default constructor
     */
    public WeaponDTO() {}

    /**
     * Constructor for searches
     * @param id
     */
    public WeaponDTO(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param xenoCharacter
     * @param weaponType
     */
    public WeaponDTO(int id, String name, XenoCharacterDTO xenoCharacter, WeaponTypeDTO weaponType) {
        this.id = id;
        this.name = name;
        this.xenoCharacter = xenoCharacter;
        this.weaponType = weaponType;
    }

    /**
     * Getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XenoCharacterDTO getXenoCharacter() {
        return xenoCharacter;
    }

    public void setXenoCharacter(XenoCharacterDTO xenoCharacter) {
        this.xenoCharacter = xenoCharacter;
    }

    public WeaponTypeDTO getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponTypeDTO weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WeaponDTO weapon = (WeaponDTO) o;
        return id == weapon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xenoCharacter=" + xenoCharacter +
                ", weaponType=" + weaponType +
                '}';
    }
}
