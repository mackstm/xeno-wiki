package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author mackstm
 */

@Entity
@Table(name = "mech_categories")
public class MechCategory {
    /**
     * Properties
     */

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "mechCategory", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Mech> mechs;

    /**
     * Default constructor
     */
    public MechCategory() {}

    /**
     * Constructor for searches
     * @param id
     */
    public MechCategory(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param mechs
     */
    public MechCategory(int id, String name, Set<Mech> mechs) {
        this.id = id;
        this.name = name;
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

    public Set<Mech> getMechs() {
        return mechs;
    }

    public void setMechs(Set<Mech> mechs) {
        this.mechs = mechs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MechCategory that = (MechCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MechCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mechs=" + mechs +
                '}';
    }
}
