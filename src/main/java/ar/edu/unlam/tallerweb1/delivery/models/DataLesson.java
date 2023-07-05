package ar.edu.unlam.tallerweb1.delivery.models;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Lugar;

import java.util.Date;

public class DataLesson {

    private Long idState;

    private Long lessonId;
    private Disciplina discipline;
    private Dificultad difficulty;
    private Detalle detail;
    private Lugar place;
    private Date date;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public Disciplina getDiscipline() {
        return discipline;
    }

    public Dificultad getDifficulty() {
        return difficulty;
    }

    public Detalle getDetail() {
        return detail;
    }

    public Lugar getPlace() {
        return place;
    }

    public Date getDate() {
        return date;
    }
}
