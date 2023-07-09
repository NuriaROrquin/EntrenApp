package ar.edu.unlam.tallerweb1.delivery.models;

import java.util.List;

public class DataPreferences {

    private Long idPreferences;
    private Long idUser;

    private List<Long> idDiscipline;


    public Long getIdPreferences() {
        return idPreferences;
    }

    public void setCalificationId(Long idPreferences) {
        this.idPreferences = idPreferences;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(String description) {
        this.idUser = idUser;
    }

    public List<Long> getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(List<Long> idDiscipline) {
        this.idDiscipline = idDiscipline;
    }
}
