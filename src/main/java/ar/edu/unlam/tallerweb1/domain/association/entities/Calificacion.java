package ar.edu.unlam.tallerweb1.domain.association.entities;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import javax.persistence.*;

@Entity
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_calificacion")
    private long idCalification;

    @Column(name="descripcion")
    private String description;

    @Column(name = "puntaje")
    private int score;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Clase idClass;

    public long getIdCalification() {
        return idCalification;
    }

    public void setIdCalification(long idCalification) {
        this.idCalification = idCalification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Clase getIdClass() {
        return idClass;
    }

    public void setIdClass(Clase idClass) {
        this.idClass = idClass;
    }
}
