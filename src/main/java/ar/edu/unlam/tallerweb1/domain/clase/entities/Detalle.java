package ar.edu.unlam.tallerweb1.domain.clase.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle")
    private long idDetail;

    @Column(name = "nombre")
    private String name;

    @Column(name = "hora_inicio")
    private Date startHour;

    @Column(name = "hora_fin")
    private Date endHour;

    @Column(name = "capacidad")
    private int capacity;

    public long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(long idDetail) {
        this.idDetail = idDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
