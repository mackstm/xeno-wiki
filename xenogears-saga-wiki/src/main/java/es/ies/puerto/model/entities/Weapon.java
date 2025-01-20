package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author mackstm
 */

@Entity
@Table(name = "weapons")
public class Weapon {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(mappedBy = "weapon", fetch = FetchType.LAZY)
    private XenoCharacter xenoCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private WeaponType weaponType;

    /**
     * Default constructor
     */
    public Weapon() {}

    /**
     * Constructor for searches
     * @param id
     */
    public Weapon(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param xenoCharacter
     * @param weaponType
     */
    public Weapon(int id, String name, XenoCharacter xenoCharacter, WeaponType weaponType) {
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

    public XenoCharacter getXenoCharacter() {
        return xenoCharacter;
    }

    public void setXenoCharacter(XenoCharacter xenoCharacter) {
        this.xenoCharacter = xenoCharacter;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
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
