package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;

public interface RepositorioDificultad {

    Dificultad get(Long idDificultad);
}
