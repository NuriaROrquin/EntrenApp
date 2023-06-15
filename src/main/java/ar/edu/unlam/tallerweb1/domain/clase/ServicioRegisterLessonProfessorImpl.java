package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("servicioRegisterLessonProfesor")

@Transactional
public class ServicioRegisterLessonProfessorImpl implements ServicioRegisterLessonProfessor {
    private RepositorioClase servicioRegisterLessonProfessorDao;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;
    private RepositorioUsuario servicioUsuarioDao;

    @Autowired
    public ServicioRegisterLessonProfessorImpl(RepositorioClase servicioRegisterLessonProfessorDao, RepositorioDetalle servicioDetalleDao, RepositorioDisciplina servicioDisciplinaDao, RepositorioDificultad servicioDificultadDao, RepositorioUsuario servicioUsuarioDao) {
        this.servicioRegisterLessonProfessorDao = servicioRegisterLessonProfessorDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
    }

    @Override
    public void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor, Long idProfessor) {
        /*Integer idDisciplina = registrarDisciplina(edadMinima, edadMaxima, nombre);*/

        Long lastInsertedIdDetail = servicioDetalleDao.create(datosRegisterLessonProfessor.getHour_ini(), datosRegisterLessonProfessor.getHour_fin(), datosRegisterLessonProfessor.getCapacity());
        Detalle detalle = servicioDetalleDao.get(lastInsertedIdDetail);
        Disciplina disciplina = servicioDisciplinaDao.get(datosRegisterLessonProfessor.getIdDiscipline());
        Dificultad dificultad = servicioDificultadDao.get(datosRegisterLessonProfessor.getIdDifficulty());
        Usuario professor = servicioUsuarioDao.getUserById(idProfessor);

        servicioRegisterLessonProfessorDao.create(dificultad, detalle, disciplina, datosRegisterLessonProfessor.getDate(), professor);
    }

}
