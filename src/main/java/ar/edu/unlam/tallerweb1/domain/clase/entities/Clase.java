package ar.edu.unlam.tallerweb1.domain.clase.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Clase {

    public Clase(long idClass, Date date, int isApproved, int isEliminated, Date openDate, Date closingDate, Detalle detail, Lugar place, Dificultad difficulty, Disciplina discipline) {
        this.idClass = idClass;
        this.date = date;
        this.isApproved = isApproved;
        this.isEliminated = isEliminated;
        this.openDate = openDate;
        this.closingDate = closingDate;
        this.detail = detail;
        this.place = place;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_clase")
    private long idClass;

    @Column(name = "fecha")
    private Date date;

    @Column(name = "isAprobada")
    private int isApproved;

    @Column(name = "isEliminada")
    private int isEliminated;

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

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public int getIsEliminated() {
        return isEliminated;
    }

    public void setIsEliminated(int isEliminated) {
        this.isEliminated = isEliminated;
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
}
