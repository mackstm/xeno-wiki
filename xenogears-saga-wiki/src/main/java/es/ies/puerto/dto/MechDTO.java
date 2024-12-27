package es.ies.puerto.dto;

import java.util.Objects;

public class MechDTO {
    /**
     * Properties
     */
    private int id;
    private String name;
    private XenoCharacterDTO xenoCharacter;
    private MechCategoryDTO mechCategory;

    /**
     * Default constructor
     */
    public MechDTO() {}

    /**
     * Constructor for searches
     * @param id
     */
    public MechDTO(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param xenoCharacter
     * @param mechCategory
     */
    public MechDTO(int id, String name, XenoCharacterDTO xenoCharacter, MechCategoryDTO mechCategory) {
        this.id = id;
        this.name = name;
        this.xenoCharacter = xenoCharacter;
        this.mechCategory = mechCategory;
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

    public XenoCharacterDTO getXenoCharacter() {
        return xenoCharacter;
    }

    public void setXenoCharacter(XenoCharacterDTO xenoCharacter) {
        this.xenoCharacter = xenoCharacter;
    }

    public MechCategoryDTO getMechCategory() {
        return mechCategory;
    }

    public void setMechCategory(MechCategoryDTO mechCategory) {
        this.mechCategory = mechCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MechDTO mech = (MechDTO) o;
        return id == mech.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Mech{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xenoCharacter=" + xenoCharacter +
                ", mechCategory=" + mechCategory +
                '}';
    }
}
