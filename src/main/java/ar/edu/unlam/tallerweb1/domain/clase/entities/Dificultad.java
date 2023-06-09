package ar.edu.unlam.tallerweb1.domain.clase.entities;

import javax.persistence.*;

@Entity
public class Dificultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_dificultad")
    private long idDifficulty;

    @Column(name = "descripcion")
    private String description;

    public long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
