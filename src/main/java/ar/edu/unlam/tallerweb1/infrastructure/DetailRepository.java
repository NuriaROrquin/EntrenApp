package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;

import java.time.LocalTime;

public interface DetailRepository {

    Long create(LocalTime hourIni, LocalTime hourFin, Integer capacity);

    Detalle get(Long detailId);

}
