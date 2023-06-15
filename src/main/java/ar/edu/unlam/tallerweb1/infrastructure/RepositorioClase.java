package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
public interface RepositorioClase   {

    void create(Integer dificultad, Integer idDetalle, Integer idDisciplina);
}
