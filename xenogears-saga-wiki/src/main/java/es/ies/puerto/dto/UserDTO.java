package es.ies.puerto.dto;

import es.ies.puerto.model.entities.User;
import jakarta.persistence.*;

import java.util.Objects;


public class UserDTO {

    private int id;
    private String username;
    private String email;
    private RoleDTO role;

    /**
     * Default constructor of the class
     */
    public UserDTO() {}

    /**
     * Constructor used for searches
     * @param id of user
     */
    public UserDTO(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param id of the user
     * @param email of the user
     */
    public UserDTO(int id, String email) {
        this.email = email;
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param username
     * @param email
     * @param role
     */
    public UserDTO(int id, String username, String email, RoleDTO role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id_role=" + role.getName() + '\''+
                '}';
    }

    /**
     * Equals and HashCode
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
