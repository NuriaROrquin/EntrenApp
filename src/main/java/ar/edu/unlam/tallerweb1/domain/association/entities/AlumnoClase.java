package ar.edu.unlam.tallerweb1.domain.association.entities;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import javax.persistence.*;

@Entity
public class AlumnoClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_clase")
    private long idUserClass;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Clase lesson;

    @OneToOne
    private Calificacion calification;

    public long getIdUserClass() {
        return idUserClass;
    }

    public void setIdUserClass(long idUserClass) {
        this.idUserClass = idUserClass;
    }

    public Clase getLesson() {
        return lesson;
    }

    public void setLesson(Clase lesson) {
        this.lesson = lesson;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Calificacion getCalification() {
        return calification;
    }

    public void setCalification(Calificacion calification) {
        this.calification = calification;
    }
}
