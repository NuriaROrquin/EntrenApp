package ar.edu.unlam.tallerweb1.delivery.models;

public class DatosRegisterLessonProfessor {


    private String date;

    private String capacity;

    private String hour_ini;

    private String hour_fin;

    private String name;

    private int age_max;

    private int age_min;

    private String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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

    public String getHour_ini() {
        return hour_ini;
    }

    public void setHour_ini(String hour_ini) {
        this.hour_ini = hour_ini;
    }

    public String getHour_fin() {
        return hour_fin;
    }

    public void setHour_fin(String hour_fin) {
        this.hour_fin = hour_fin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
