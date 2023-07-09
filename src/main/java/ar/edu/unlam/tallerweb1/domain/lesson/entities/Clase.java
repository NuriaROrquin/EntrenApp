package ar.edu.unlam.tallerweb1.domain.lesson.entities;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private long idClass;

    @Column(name = "fecha")
    private Date date;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date openDate;

    @Column(name = "fecha_baja")
    private Date closingDate;

    @Column(name = "is_calificada")
    private Boolean isCalificated;

    @ManyToOne
    private Estado state;

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

    @Column(name = "edad_minima")
    private Integer minimum_age;

    @Column(name = "edad_maxima")
    private Integer maximum_age;

    @Column(name = "nombre")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdClass() {
        return idClass;
    }

    public void setIdClass(long idClass) {
        this.idClass = idClass;
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr() {
        return date.toString();
    }

    public Date getDateFormat() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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

    public Estado getState() {
        return state;
    }

    public void setState(Estado state) {
        this.state = state;
    }

    public Boolean getCalificated() {
        return isCalificated;
    }

    public void setCalificated(Boolean calificated) {
        isCalificated = calificated;
    }
}
