package es.ies.puerto.dto;


import java.util.Objects;
import java.util.Set;

public class MechCategoryDTO {
    /**
     * Properties
     */

    private int id;
    private String name;

    /**
     * Default constructor
     */
    public MechCategoryDTO() {}

    /**
     * Constructor for searches
     * @param id
     */
    public MechCategoryDTO(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MechCategoryDTO that = (MechCategoryDTO) o;
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
                '}';
    }
}
