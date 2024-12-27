package es.ies.puerto.model.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


import jakarta.persistence.*;


/**
 * @author mackstm
 */

@Entity
@Table(name = "xeno_characters")
public class XenoCharacter implements Serializable {
    /**
     * Properties
     */
    @Id
    int id;
    String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;

    @OneToMany(mappedBy = "xenoCharacter", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Mech> mechs;


    /**
     * Default constructor
     */
    public XenoCharacter() {
    }

    /**
     * Constructor for searches
     * @param id
     */
    public XenoCharacter(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param weapon
     * @param mechs
     */
    public XenoCharacter(int id, String name, Weapon weapon, Set<Mech> mechs) {
        this.id = id;
        this.name = name;
        this.weapon = weapon;
        this.mechs = mechs;
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Set<Mech> getMechs() {
        return mechs;
    }

    public void setMechs(Set<Mech> mechs) {
        this.mechs = mechs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        XenoCharacter that = (XenoCharacter) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "XenoCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weapon=" + weapon +
                ", mech=" + mechs +
                '}';
    }
}
