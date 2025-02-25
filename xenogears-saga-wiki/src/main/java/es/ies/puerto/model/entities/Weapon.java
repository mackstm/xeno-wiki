package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author mackstm
 */

@Entity
@Table(name = "weapons")
public class Weapon implements Serializable {
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
