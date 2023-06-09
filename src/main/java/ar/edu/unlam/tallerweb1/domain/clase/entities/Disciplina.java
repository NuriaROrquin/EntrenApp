package ar.edu.unlam.tallerweb1.domain.clase.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_disciplina")
    private long idDiscipline;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    @Type(type="text")
    private TextArea description;

    @Column(name = "edad_minima")
    private int minimum_age;

    @Column(name = "edad_maxima")
    private int maximum_age;

    public long getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(long idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea description) {
        this.description = description;
    }

    public int getMinimum_age() {
        return minimum_age;
    }

    public void setMinimum_age(int minimum_age) {
        this.minimum_age = minimum_age;
    }

    public int getMaximum_age() {
        return maximum_age;
    }

    public void setMaximum_age(int maximum_age) {
        this.maximum_age = maximum_age;
    }
}
