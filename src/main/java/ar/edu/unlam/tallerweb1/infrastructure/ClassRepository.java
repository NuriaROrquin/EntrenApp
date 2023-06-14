package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.UsuarioClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;

import java.util.List;

public interface ClassRepository {

    List<UsuarioClase> getClassesByIdAlumno(Integer idUser);
}
