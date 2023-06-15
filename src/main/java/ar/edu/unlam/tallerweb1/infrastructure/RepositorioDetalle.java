package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;

import java.sql.Time;

public interface RepositorioDetalle {

    Integer create(String hourIni, String hourFin, String capacity);

    Detalle get(Integer idDetalle);
}
