package es.ies.puerto.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author mackstm
 */

@Entity
@Table(name = "mechs")
public class Mech implements Serializable {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_id")
    private XenoCharacter xenoCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private MechCategory mechCategory;

    /**
     * Default constructor
     */
    public Mech() {}

    /**
     * Constructor for searches
     * @param id
     */
    public Mech(int id) {
        this.id = id;
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param xenoCharacter
     * @param mechCategory
     */
    public Mech(int id, String name, XenoCharacter xenoCharacter, MechCategory mechCategory) {
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

    public XenoCharacter getXenoCharacter() {
        return xenoCharacter;
    }

    public void setXenoCharacter(XenoCharacter xenoCharacter) {
        this.xenoCharacter = xenoCharacter;
    }

    public MechCategory getMechCategory() {
        return mechCategory;
    }

    public void setMechCategory(MechCategory mechCategory) {
        this.mechCategory = mechCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mech mech = (Mech) o;
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
