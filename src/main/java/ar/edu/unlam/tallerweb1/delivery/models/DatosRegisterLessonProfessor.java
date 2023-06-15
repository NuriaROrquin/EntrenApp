package ar.edu.unlam.tallerweb1.delivery.models;

import java.util.Date;

public class DatosRegisterLessonProfessor {


    private String date;

    private int capacity;

    private Date hour_ini;

    private Date hour_fin;

    private String name;

    private int age_max;

    private int age_min;

    private Integer idDifficulty;

    private Integer idDiscipline;

    private Integer idDetail;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getHour_ini() {
        return hour_ini;
    }

    public void setHour_ini(Date hour_ini) {
        this.hour_ini = hour_ini;
    }

    public Date getHour_fin() {
        return hour_fin;
    }

    public void setHour_fin(Date hour_fin) {
        this.hour_fin = hour_fin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public Integer getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Integer idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public Integer getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(Integer idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }
}
