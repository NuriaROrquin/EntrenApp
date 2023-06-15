package ar.edu.unlam.tallerweb1.delivery.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DatosRegisterLessonProfessor {

    private String date;

    private Integer capacity;

    private String hour_ini;

    private String hour_fin;

    private String name;

    private Integer age_max;

    private Integer age_min;

    private Long idDifficulty;

    private Long idDiscipline;

    public String getDateStr() {
        return date;
    }

    public void setDateStr(String dateStr) {
        this.date = dateStr;
    }

    public Date getDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = dateFormat.format(date);
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getHour_iniString() {
        return hour_ini;
    }

    public void setHour_iniString(String hour_ini) {
        this.hour_ini = hour_ini;
    }

    public String getHour_finString() {
        return hour_fin;
    }

    public void setHour_finString(String hour_fin) {
        this.hour_fin = hour_fin;
    }

    public LocalTime getHour_ini() {
        return LocalTime.parse(hour_ini);
    }

    public LocalTime getHour_fin() {
        return LocalTime.parse(hour_fin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge_max() {
        return age_max;
    }

    public void setAge_max(Integer age_max) {
        this.age_max = age_max;
    }

    public Integer getAge_min() {
        return age_min;
    }

    public void setAge_min(Integer age_min) {
        this.age_min = age_min;
    }

    public Long getIdDifficulty() {
        return idDifficulty;
    }

    public void setIdDifficulty(Long idDifficulty) {
        this.idDifficulty = idDifficulty;
    }

    public Long getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(Long idDiscipline) {
        this.idDiscipline = idDiscipline;
    }
}
