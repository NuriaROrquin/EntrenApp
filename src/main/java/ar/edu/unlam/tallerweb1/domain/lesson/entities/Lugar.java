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
    private Double latitude;

    @Column(name = "longitud")
    private Double longitude;

    @Column(name = "nombre")
    private String name;

    public long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(long idPlace) {
        this.idPlace = idPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
