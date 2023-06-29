package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioClase")
@Transactional
public class LessonServiceImpl implements LessonService {

    private LessonRepository repositoryClass;
    private RepositorioUsuario servicioUsuarioDao;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;
    private PlaceRepository servicePlaceDao;

    private StateRepository serviceStateDao;

    @Autowired
    public LessonServiceImpl(LessonRepository servicioClaseDao, RepositorioUsuario servicioUsuarioDao, RepositorioDetalle servicioDetalleDao, RepositorioDisciplina servicioDisciplinaDao, RepositorioDificultad servicioDificultadDao, PlaceRepository servicePlaceDao, StateRepository serviceStateDao) {

        this.repositoryClass = servicioClaseDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
        this.servicePlaceDao = servicePlaceDao;
        this.serviceStateDao = serviceStateDao;
    }

    @Override
    public List<Clase> getLessonsByStudentId(Long idStudent) {
        Usuario student = servicioUsuarioDao.getUserById(idStudent);
        List<Clase> lessons = repositoryClass.getClassesByIdAlumno(student);
        return lessons;
    }

    @Override
    public List<Clase> getLessonsByProfessorId(Long id) {
        Usuario professor = servicioUsuarioDao.getUserById(id);
        List<Clase> lessons = repositoryClass.getClassesByProfessorId(professor);
        return lessons;
    }

    @Override
    public void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor, Long idProfessor) {
        /*Integer idDisciplina = registrarDisciplina(edadMinima, edadMaxima, nombre);*/

        Long lastInsertedIdDetail = servicioDetalleDao.create(datosRegisterLessonProfessor.getHour_ini(), datosRegisterLessonProfessor.getHour_fin(), datosRegisterLessonProfessor.getCapacity());
        Detalle detalle = servicioDetalleDao.get(lastInsertedIdDetail);
        Disciplina disciplina = servicioDisciplinaDao.get(datosRegisterLessonProfessor.getIdDiscipline());
        Dificultad dificultad = servicioDificultadDao.get(datosRegisterLessonProfessor.getIdDifficulty());
        Lugar place = servicePlaceDao.get(datosRegisterLessonProfessor.getIdLugar());
        Usuario professor = servicioUsuarioDao.getUserById(idProfessor);

        repositoryClass.create(dificultad, detalle, disciplina, place, datosRegisterLessonProfessor.getDate(), professor);
    }

    @Override
    public List<Clase> getLessonsInStateFinishedFromProfessor(Long id, long idState) {
        Usuario professor = servicioUsuarioDao.getUserById(id);
        Estado state = serviceStateDao.getStateById(idState);
        List<Clase> lessons = repositoryClass.getLessonsInStateFinishedByProfessorId(professor, state);
        return lessons;
    }


}
