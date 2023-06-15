package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.Date;

public interface RepositorioClase   {

    void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Date date, Usuario professor);
}
