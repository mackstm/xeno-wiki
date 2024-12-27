package es.ies.puerto.dto;


import java.util.Objects;

public class WeaponTypeDTO {
    /**
     * Properties
     */
    private int id;
    private String name;

    /**
     * Default constructor
     */
    public WeaponTypeDTO() {}

    /**
     * Constructor for searches
     * @param id
     */
    public WeaponTypeDTO(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     */
    public WeaponTypeDTO(int id, String name) {
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
        WeaponTypeDTO that = (WeaponTypeDTO) o;
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
                '}';
    }
}
