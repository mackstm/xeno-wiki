package es.ies.puerto.dto;

import es.ies.puerto.model.entities.User;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


public class RoleDTO {

    private int id;

    private String name;

    /**
     * Default constructor of the class
     */
    public RoleDTO() {}

    /**
     * Constructor used for searches
     * @param id of role
     */
    public RoleDTO(int id) {
        this.id = id;
    }

    /**
     * Full constructor of the clss
     * @param id of the user
     * @param name of the user
     */
    public RoleDTO(int id, String name) {
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

    /**
     * ToString
     */
    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Equals and hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO role = (RoleDTO) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
