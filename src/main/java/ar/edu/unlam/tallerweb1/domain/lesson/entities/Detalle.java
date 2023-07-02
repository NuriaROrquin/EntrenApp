package ar.edu.unlam.tallerweb1.domain.lesson.entities;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle")
    private long idDetail;

    @Column(name = "hora_inicio")
    private LocalTime startHour;

    @Column(name = "hora_fin")
    private LocalTime endHour;

    @Column(name = "capacidad")
    private int capacity;

    public long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(long idDetail) {
        this.idDetail = idDetail;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
