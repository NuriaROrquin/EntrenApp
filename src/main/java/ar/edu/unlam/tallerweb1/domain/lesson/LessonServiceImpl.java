package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
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
    private UserRepository servicioUsuarioDao;
    private DetailRepository servicioDetalleDao;
    private DisciplineRepository servicioDisciplinaDao;
    private DifficultyRepository servicioDificultadDao;
    private PlaceRepository servicePlaceDao;

    private StateRepository serviceStateDao;

    @Autowired
    public LessonServiceImpl(LessonRepository servicioClaseDao, UserRepository servicioUsuarioDao, DetailRepository servicioDetalleDao, DisciplineRepository servicioDisciplinaDao, DifficultyRepository servicioDificultadDao, PlaceRepository servicePlaceDao, StateRepository serviceStateDao) {

        this.serviceLessonDao = servicioClaseDao;
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
        List<Clase> lessons = serviceLessonDao.getLessonsByStudent(student);
        return lessons;
    }

    @Override
    public List<Clase> getLessonsByProfessorId(Long id) {
        Usuario professor = servicioUsuarioDao.getUserById(id);
        List<Clase> lessons = serviceLessonDao.getLessonsByProfessor(professor);
        return lessons;
    }

    @Override
    public void registerLesson(DataLessonRegistration dataLessonRegistration, Long idProfessor) {
        /*Integer idDisciplina = registrarDisciplina(edadMinima, edadMaxima, nombre);*/

        Long lastInsertedIdDetail = servicioDetalleDao.create(dataLessonRegistration.getHour_ini(), dataLessonRegistration.getHour_fin(), dataLessonRegistration.getCapacity());
        Detalle detalle = servicioDetalleDao.get(lastInsertedIdDetail);
        Disciplina disciplina = servicioDisciplinaDao.get(dataLessonRegistration.getIdDiscipline());
        Dificultad dificultad = servicioDificultadDao.get(dataLessonRegistration.getIdDifficulty());
        Lugar place = servicePlaceDao.getPlaceById(dataLessonRegistration.getIdLugar());
        Usuario professor = servicioUsuarioDao.getUserById(idProfessor);

        serviceLessonDao.create(dificultad, detalle, disciplina, place, dataLessonRegistration.getDate(), professor);
    }

    @Override
    public List<Clase> getLessonsByState(Long userId, Long stateId) {
        Usuario user = servicioUsuarioDao.getUserById(userId);
        Estado state = serviceStateDao.getStateById(stateId);

        List<Clase> lessons;

        if(user.getRol().getDescription().equals("profesor")){
            lessons = serviceLessonDao.getLessonsByStateAndProfessor(user, state);
        }else{
            lessons = serviceLessonDao.getLessonsByStateAndStudent(user, state);
        }

        return lessons;
    }

    @Override
    public List<Clase> cancelLesson(Long lessonId, Long userId) {
        Usuario user = servicioUsuarioDao.getUserById(userId);
        Clase lesson = serviceLessonDao.getLessonById(lessonId);
        String role = user.getRol().getDescription();
        List<Clase> lessons;
        if (role.equals("profesor")) {
            serviceLessonDao.cancelLessonByProfessor(lesson, user);
            lessons = serviceLessonDao.getLessonsByProfessor(user);
            return lessons;
        }/*else {
            serviceLessonDao.cancelLessonByStudent(lesson,user);
        }*/
        return null;
    }


}
