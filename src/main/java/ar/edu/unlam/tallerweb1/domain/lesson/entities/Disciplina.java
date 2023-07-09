package ar.edu.unlam.tallerweb1.domain.lesson.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disciplina")
    private long idDiscipline;

    @Column(name = "descripcion")
    @Type(type = "text")
    private String description;

    public long getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(long idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
