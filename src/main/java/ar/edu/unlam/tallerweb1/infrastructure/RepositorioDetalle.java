package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public interface RepositorioDetalle {

    Long create(LocalTime hourIni, LocalTime hourFin, Integer capacity);

    Detalle get(Long idDetalle);

}
