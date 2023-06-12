package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;

import java.util.List;

public interface ClassRepository {

    List<Clase> getClasses(Integer idUser);
}
