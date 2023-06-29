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

    private LessonRepository serviceLessonDao;
    private RepositorioUsuario servicioUsuarioDao;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;
    private PlaceRepository servicePlaceDao;

    private StateRepository serviceStateDao;

    @Autowired
    public LessonServiceImpl(LessonRepository servicioClaseDao, RepositorioUsuario servicioUsuarioDao, RepositorioDetalle servicioDetalleDao, RepositorioDisciplina servicioDisciplinaDao, RepositorioDificultad servicioDificultadDao, PlaceRepository servicePlaceDao, StateRepository serviceStateDao) {

        this.serviceLessonDao = servicioClaseDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
        this.servicePlaceDao = servicePlaceDao;
        this.serviceStateDao = serviceStateDao;
    }

    @Override
    public List<AlumnoClase> getClassesByIdAlumno(Usuario alumno) {
        return serviceLessonDao.getClassesByIdAlumno(alumno);
    }

    @Override
    public List<Clase> getLessonsByProfessorId(Long id) {
        Usuario professor = servicioUsuarioDao.getUserById(id);
        List<Clase> lessons = serviceLessonDao.getClassesByProfessorId(professor);
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

        serviceLessonDao.create(dificultad, detalle, disciplina, place, datosRegisterLessonProfessor.getDate(), professor);
    }

    @Override
    public List<Clase> getLessonsDependingStateFromProfessor(Long id, Long idState){
        List <Clase> lessons = new ArrayList<>();
        Usuario professor = servicioUsuarioDao.getUserById(id);
        Estado state = serviceStateDao.getStateById(idState);
        lessons = serviceLessonDao.getLessonsDependingStateFromProfessor(professor,state);
        return lessons;
    }


}
