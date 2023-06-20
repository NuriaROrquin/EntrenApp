package ar.edu.unlam.tallerweb1.helpers;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.Date;

public class BasicData {

    public Clase createClase(int id, Disciplina discipline, Date date, Detalle detail, Usuario professor) {

        Clase lesson = new Clase();
        lesson.setIdClass(id);
        lesson.setDiscipline(discipline);
        lesson.setDate(date);
        lesson.setDetail(detail);
        lesson.setProfesor(professor);
        return lesson;
    }


}
