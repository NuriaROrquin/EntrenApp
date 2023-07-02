package ar.edu.unlam.tallerweb1.domain.lesson.entities;

import javax.persistence.*;
@Entity
public class Estado {
    public Estado(long idState, String description) {
        this.idState = idState;
        this.description = description;
    }
    public Estado (){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private long idState;

    @Column(name = "descripcion")
    private String description;

    public long getIdState() {
        return idState;
    }

    public void setIdState(long idState) {
        this.idState = idState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}






