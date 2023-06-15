package ar.edu.unlam.tallerweb1.delivery.models;

public class DatosRegisterLessonStudent {

    private String professor;

    private String date;

    private String disiplina;

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisiplina() {
        return disiplina;
    }

    public void setDisiplina(String disiplina) {
        this.disiplina = disiplina;
    }
}
