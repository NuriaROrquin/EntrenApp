package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;

import java.sql.Time;

public interface RepositorioDetalle {

    Integer create(Time hourIni, Time hourFin, Integer capacity);
}
