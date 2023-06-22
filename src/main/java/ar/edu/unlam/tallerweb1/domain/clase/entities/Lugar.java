package ar.edu.unlam.tallerweb1.domain.clase.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;

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

    @Column(name = "descripcion")
    @Type(type="text")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
