package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.infrastructure.RepositorioClase;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioDetalle;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;


@Service ("servicioRegisterLessonProfesor")

    @Transactional
    public class ServicioRegisterLessonProfessorImpl implements ServicioRegisterLessonProfessor {
        private RepositorioClase servicioRegisterLessonProfessorDao;
        private RepositorioDetalle servicioDetalleDao;
        private RepositorioDisciplina servicioDisciplinaDao;

        @Autowired
        public ServicioRegisterLessonProfessorImpl(RepositorioClase servicioRegisterLessonProfessorDao, RepositorioDetalle servicioDetalleDao, RepositorioDisciplina servicioDisciplinaDao) {
            this.servicioRegisterLessonProfessorDao = servicioRegisterLessonProfessorDao;
            this.servicioDetalleDao = servicioDetalleDao;
            this.servicioDisciplinaDao = servicioDisciplinaDao;
        }

        @Override
        public void registerLesson (Time hour_ini, Time hour_fin, Integer capacity, Integer edadMinima, Integer edadMaxima, String nombre, Integer dificultad) {
            Integer idDetalle = registrarDetalle(hour_ini, hour_fin, capacity);
            Integer idDisciplina = registrarDisciplina(edadMinima, edadMaxima, nombre);

            servicioRegisterLessonProfessorDao.create(dificultad, idDetalle, idDisciplina);


        }
        public Integer registrarDetalle(Time hour_ini, Time hour_fin, Integer capacity) {
            Integer lastIdDetalle;

            return lastIdDetalle = servicioDetalleDao.create(hour_ini, hour_fin, capacity);
        }
        public Integer registrarDisciplina(Integer edadMinima, Integer edadMaxima, String nombre) {
            Integer lastIdDisciplina;

            return lastIdDisciplina = servicioDisciplinaDao.create(edadMinima, edadMaxima, nombre);
        }


    }
