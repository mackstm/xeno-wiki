package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
/**
 * @author mackstm
 */

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    /**
     * Properties
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    Set<User> users;

    /**
     * Default constructor of the class
     */
    public Role() {}

    /**
     * Constructor used for searches
     * @param id of role
     */
    public Role(int id) {
        this.id = id;
    }

    /**
     * Full constructor of the class
     * @param id of the role
     * @param name of the role
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
