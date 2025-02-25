package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author mackstm
 */

@Entity
@Table(name = "weapon_types")
public class WeaponType implements Serializable {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "weaponType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Weapon> weapons;

    /**
     * Default constructor
     */
    public WeaponType() {}

    /**
     * Constructor for searches
     * @param id
     */
    public WeaponType(int id) {
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

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(Set<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WeaponType that = (WeaponType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WeaponType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weapons=" + weapons +
                '}';
    }
}
