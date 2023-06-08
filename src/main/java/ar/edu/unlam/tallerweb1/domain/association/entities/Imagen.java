package ar.edu.unlam.tallerweb1.domain.association.entities;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Lugar;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Imagen {

    public Imagen(long idImage, String name, TextArea description, Disciplina discipline, Lugar place) {
        this.idImage = idImage;
        this.name = name;
        this.description = description;
        this.discipline = discipline;
        this.place = place;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private long idImage;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private TextArea description;

    @ManyToOne
    private Disciplina discipline;

    @ManyToOne
    private Lugar place;

    public long getIdImage() {
        return idImage;
    }

    public void setIdImage(long idImage) {
        this.idImage = idImage;
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

    public Disciplina getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplina discipline) {
        this.discipline = discipline;
    }

    public Lugar getPlace() {
        return place;
    }

    public void setPlace(Lugar place) {
        this.place = place;
    }
}
