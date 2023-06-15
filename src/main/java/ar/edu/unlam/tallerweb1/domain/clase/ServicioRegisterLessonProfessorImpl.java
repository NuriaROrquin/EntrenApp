package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioClase;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioDetalle;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioDificultad;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;


@Service("servicioRegisterLessonProfesor")

@Transactional
public class ServicioRegisterLessonProfessorImpl implements ServicioRegisterLessonProfessor {
    private RepositorioClase servicioRegisterLessonProfessorDao;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;

    @Autowired
    public ServicioRegisterLessonProfessorImpl(RepositorioClase servicioRegisterLessonProfessorDao, RepositorioDetalle servicioDetalleDao, RepositorioDisciplina servicioDisciplinaDao, RepositorioDificultad servicioDificultadDao) {
        this.servicioRegisterLessonProfessorDao = servicioRegisterLessonProfessorDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
    }

    @Override
    public void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor) {
            /*Integer idDetalle = registrarDetalle(lesson.getHour_ini(), lesson.getHour_fin(), lesson.getCapacity());
            Integer idDisciplina = registrarDisciplina(edadMinima, edadMaxima, nombre);*/

        Detalle detalle = servicioDetalleDao.get(datosRegisterLessonProfessor.getIdDetail());
        Disciplina disciplina = servicioDisciplinaDao.get(datosRegisterLessonProfessor.getIdDiscipline());
        Dificultad dificultad = servicioDificultadDao.get(datosRegisterLessonProfessor.getIdDifficulty());

        servicioRegisterLessonProfessorDao.create(dificultad, detalle, disciplina);
    }

        /*public Integer registrarDetalle(String hour_ini, String hour_fin, String capacity) {
            Integer lastIdDetalle;

            return lastIdDetalle = servicioDetalleDao.create(hour_ini, hour_fin, capacity);
        }
        public Integer registrarDisciplina(Integer edadMinima, Integer edadMaxima, String nombre) {
            Integer lastIdDisciplina;

            return lastIdDisciplina = servicioDisciplinaDao.create(edadMinima, edadMaxima, nombre);
        }*/


}
