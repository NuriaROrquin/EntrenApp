package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service("servicioClase")
@Transactional
public class LessonServiceImpl implements LessonService {

    private CalificationRepository serviceCalificationDao;
    private LessonRepository serviceLessonDao;
    private UserRepository servicioUsuarioDao;
    private DetailRepository servicioDetalleDao;
    private DisciplineRepository servicioDisciplinaDao;
    private DifficultyRepository servicioDificultadDao;
    private PlaceRepository servicePlaceDao;

    private StateRepository serviceStateDao;

    @Autowired
    public LessonServiceImpl(LessonRepository servicioClaseDao, UserRepository servicioUsuarioDao, DetailRepository servicioDetalleDao, DisciplineRepository servicioDisciplinaDao, DifficultyRepository servicioDificultadDao, PlaceRepository servicePlaceDao, StateRepository serviceStateDao, CalificationRepository serviceCalificationDao) {

        this.serviceLessonDao = servicioClaseDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
        this.servicePlaceDao = servicePlaceDao;
        this.serviceStateDao = serviceStateDao;
        this.serviceCalificationDao = serviceCalificationDao;
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
        Long lastInsertedIdDetail = servicioDetalleDao.create(dataLessonRegistration.getHour_ini(), dataLessonRegistration.getHour_fin(), dataLessonRegistration.getCapacity());
        Detalle detalle = servicioDetalleDao.get(lastInsertedIdDetail);
        Disciplina disciplina = servicioDisciplinaDao.get(dataLessonRegistration.getIdDiscipline());
        Dificultad dificultad = servicioDificultadDao.get(dataLessonRegistration.getIdDifficulty());
        Lugar place = servicePlaceDao.getPlaceById(dataLessonRegistration.getIdLugar());
        Estado state = serviceStateDao.getStateById(1L);
        Usuario professor = servicioUsuarioDao.getUserById(idProfessor);
        Date date = dataLessonRegistration.getDate();
        Integer minimumAge = dataLessonRegistration.getAge_min();
        Integer maximumAge = dataLessonRegistration.getAge_max();
        String className = dataLessonRegistration.getName();

        serviceLessonDao.create(dificultad, detalle, disciplina, place, date, professor, minimumAge, maximumAge, className, state);
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

    @Override
    public List<Dificultad> getAllDifficulties() {
        List<Dificultad> difficulties = servicioDificultadDao.getAllTheDifficulties();

        return difficulties;
    }

    @Override
    public List<Disciplina> getAllDisciplines() {
        List<Disciplina> disciplines = servicioDisciplinaDao.getAllTheDisciplines();

        return disciplines;
    }

    @Override
    public List<Lugar> getAllDPlaces() {
        List<Lugar> places = servicePlaceDao.getAllThePlaces();

        return places;
    }

    @Override
    public List<Clase> calificateLessonByStudent(Long lessonId, DataCalification dataCalification, Long studentId){
        Usuario user = servicioUsuarioDao.getUserById(studentId);
        Clase lesson = serviceLessonDao.getLessonById(lessonId);
        Calificacion calification = serviceCalificationDao.create(dataCalification.getDescription(), dataCalification.getScore(), lesson, user);
        serviceLessonDao.calificateLessonByStudent(lesson,calification,user);
        List<Clase> lessonResult = serviceLessonDao.getLessonsByStudent(user);
        return lessonResult;
    }

    @Override
    public List<Clase> modifyLesson(DataLesson dataLesson, Long professorId){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Date date = null;
        LocalTime hour_ini = null;
        LocalTime hour_fin = null;

        try {
            date = dateFormat.parse(dataLesson.getDate());
            hour_ini = LocalTime.parse(dataLesson.getHour_iniString(), formatter);
            hour_fin = LocalTime.parse(dataLesson.getHour_finString(), formatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Usuario user = servicioUsuarioDao.getUserById(professorId);
        Clase lesson = serviceLessonDao.getLessonById(dataLesson.getLessonId());

        Detalle detail = new Detalle();

        detail.setIdDetail(dataLesson.getLessonId());
        detail.setCapacity(dataLesson.getCapacity());
        detail.setStartHour(hour_ini);
        detail.setEndHour(hour_fin);

        Disciplina discipline = servicioDisciplinaDao.get(dataLesson.getIdDiscipline());
        Dificultad difficulty = servicioDificultadDao.get(dataLesson.getIdDifficulty());
        Lugar place = servicePlaceDao.getPlaceById(dataLesson.getIdLugar());

        List<Clase> lessons;

        servicioDetalleDao.modify(detail);
        serviceLessonDao.modify(difficulty,discipline,place,date,lesson,user);
        lessons = serviceLessonDao.getLessonsByProfessor(user);
        return lessons;
    }

    @Override
    public DataLessonRegistration getLessonById(Long idLesson){
        Clase lesson = serviceLessonDao.getLessonById(idLesson);

        DataLessonRegistration dataLesson = new DataLessonRegistration();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(lesson.getDate());

        dataLesson.setDateStr(formattedDate);
        dataLesson.setCapacity(lesson.getDetail().getCapacity());
        dataLesson.setAge_max(lesson.getMaximum_age());
        dataLesson.setAge_min(lesson.getMinimum_age());
        dataLesson.setIdDifficulty(lesson.getDifficulty().getIdDifficulty());
        dataLesson.setIdDiscipline(lesson.getDiscipline().getIdDiscipline());
        dataLesson.setIdLugar(lesson.getPlace().getIdPlace());
        dataLesson.setHour_iniString(lesson.getDetail().getStartHour().toString());
        dataLesson.setHour_finString(lesson.getDetail().getEndHour().toString());

        return dataLesson;
    }

    @Override
    public List<Clase> getAllAvailablesLesson(Long studentId)
    {
        Usuario student = servicioUsuarioDao.getUserById(studentId);

        List<Clase> lessons = serviceLessonDao.getAllAvailablesLesson(student);

        return lessons;
    }
}
