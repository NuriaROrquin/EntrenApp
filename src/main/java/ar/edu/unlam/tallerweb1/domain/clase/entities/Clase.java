package ar.edu.unlam.tallerweb1.domain.clase.entities;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_clase")
    private long idClass;

    @Column(name = "fecha")
    private Date date;

    @Column(name="fecha_alta")
    private Date openDate;

    @Column(name = "fecha_baja")
    private Date closingDate;

    @ManyToOne
    private Detalle detail;

    @ManyToOne
    private Lugar place;

    @ManyToOne
    private Dificultad difficulty;

    @ManyToOne
    private Disciplina discipline;

    @ManyToOne
    private Usuario professor;

    public long getIdClass() {
        return idClass;
    }

    public void setIdClass(long idClass) {
        this.idClass = idClass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Detalle getDetail() {
        return detail;
    }

    public void setDetail(Detalle detail) {
        this.detail = detail;
    }

    public Lugar getPlace() {
        return place;
    }

    public void setPlace(Lugar place) {
        this.place = place;
    }

    public Dificultad getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Dificultad difficulty) {
        this.difficulty = difficulty;
    }

    public Disciplina getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplina discipline) {
        this.discipline = discipline;
    }

    public Usuario getProfesor() {
        return professor;
    }

    public void setProfesor(Usuario profesor) {
        this.professor = profesor;
    }
}
