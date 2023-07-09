package ar.edu.unlam.tallerweb1.domain.association.entities;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import javax.persistence.*;

@Entity
public class Preferencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preferences")
    private long idPreferences;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Disciplina discipline;


    public long getIdPreferences() {
        return idPreferences;
    }

    public void setIdPreferences(long idPreferences) {
        this.idPreferences = idPreferences;
    }

    public Disciplina getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplina lesson) {
        this.discipline = lesson;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
