package es.ies.puerto.dto;

import java.util.Objects;

/**
 * @author mackstm
 */
public class XenoCharacterDTO {
    /**
     * Properties
     */
    int id;
    String name;

    /**
     * Default constructor of the class
     */
    public XenoCharacterDTO() {
    }

    /**
     * Constructor of the class
     * @param id
     */
    public XenoCharacterDTO(int id) {
        this.id = id;
    }

    /**
     * Full constructor of the class
     * @param id
     * @param name
     */

    public XenoCharacterDTO(int id, String name) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XenoCharacterDTO that = (XenoCharacterDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MGSCharacterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
