package ar.edu.unlam.tallerweb1.delivery.models;

import com.fasterxml.jackson.databind.node.DoubleNode;

import javax.persistence.Column;
import java.time.LocalTime;
import java.util.Date;

public class DataLesson {
    private Long idState;
    private Long lessonId;
    private Integer capacity;
    private LocalTime hour_ini;
    private LocalTime hour_fin;
    private String hour_iniString;
    private String hour_finString;
    private Long idDiscipline;
    private Long idLugar;
    private Long idDifficulty;
    private String date;
    private String name;
    private Integer minimum_age;
    private Integer maximum_age;
    private String address;
    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMinimum_age() {
        return minimum_age;
    }

    public void setMinimum_age(Integer minimum_age) {
        this.minimum_age = minimum_age;
    }

    public Integer getMaximum_age() {
        return maximum_age;
    }

    public void setMaximum_age(Integer maximum_age) {
        this.maximum_age = maximum_age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(Long idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public Long getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Long idLugar) {
        this.idLugar = idLugar;
    }

    public Long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalTime getHour_ini() {
        return hour_ini;
    }

    public void setHour_ini(LocalTime hour_ini) {
        this.hour_ini = hour_ini;
    }

    public LocalTime getHour_fin() {
        return hour_fin;
    }

    public void setHour_fin(LocalTime hour_fin) {
        this.hour_fin = hour_fin;
    }

    public String getHour_iniString() {
        return hour_iniString;
    }

    public void setHour_iniString(String hour_iniString) {
        this.hour_iniString = hour_iniString;
    }

    public String getHour_finString() {
        return hour_finString;
    }

    public void setHour_finString(String hour_finString) {
        this.hour_finString = hour_finString;
    }
}
