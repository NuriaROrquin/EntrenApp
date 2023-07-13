package ar.edu.unlam.tallerweb1.domain.lesson.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_lugar")
    private long idPlace;

    @Column(name = "latitud")
    private long latitude;

    @Column(name = "longitud")
    private long longitude;

    @Column(name = "nombre")
    private String name;

    public long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(long idPlace) {
        this.idPlace = idPlace;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
