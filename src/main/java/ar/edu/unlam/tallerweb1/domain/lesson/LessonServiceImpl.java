package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
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
    private PreferencesRepository servicePreferencesDao;
    private StateRepository serviceStateDao;

    @Autowired
    public LessonServiceImpl(LessonRepository servicioClaseDao, UserRepository servicioUsuarioDao, DetailRepository servicioDetalleDao, DisciplineRepository servicioDisciplinaDao, DifficultyRepository servicioDificultadDao, PlaceRepository servicePlaceDao, StateRepository serviceStateDao, CalificationRepository serviceCalificationDao, PreferencesRepository servicePreferencesDao) {

        this.serviceLessonDao = servicioClaseDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
        this.servicioDetalleDao = servicioDetalleDao;
        this.servicioDisciplinaDao = servicioDisciplinaDao;
        this.servicioDificultadDao = servicioDificultadDao;
        this.servicePlaceDao = servicePlaceDao;
        this.serviceStateDao = serviceStateDao;
        this.serviceCalificationDao = serviceCalificationDao;
        this.servicePreferencesDao = servicePreferencesDao;
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
        Detalle detalle = servicioDetalleDao.getById(lastInsertedIdDetail);
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

        if (user.getRol().getDescription().equals("profesor")) {
            lessons = serviceLessonDao.getLessonsByStateAndProfessor(user, state);
        } else {
            lessons = serviceLessonDao.getLessonsByStateAndStudent(user, state);
            /*List<Calificacion> studentCalifications = serviceLessonDao.getLessonsWithCalificationsReferToStudent(user);
            for (int i = 0; i<lessons.size(); i++){
                for (int j = 0; j<studentCalifications.size(); j++){
                    if (studentCalifications.get(j).getLesson() == lessons.get(i)){
                        lessons.get(i).setCalificated(true);
                    }
                }
            }*/
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
    public List<Disciplina> getPreferencesOrAllDisciplines(Long userId) {

        List<Preferencias> preferences = servicePreferencesDao.getPreferredDisciplinesById(userId);
        List<Disciplina> disciplines = servicioDisciplinaDao.getAllTheDisciplines();

        if (preferences != null) {
            for (int i = 0; i < disciplines.size(); i++) {
                disciplines.get(i).setPreferred(false);
                for (int j = 0; j < preferences.size(); j++) {
                    if (disciplines.get(i).getIdDiscipline() == preferences.get(j).getDiscipline().getIdDiscipline()) {
                        disciplines.get(i).setPreferred(true);
                    }
                }
            }
        }

        return disciplines;
    }

    @Override
    public List<Lugar> getAllPlaces() {
        List<Lugar> places = servicePlaceDao.getAllThePlaces();

        return places;
    }

    @Override
    public List<Clase> calificateLessonByStudent(DataCalification dataCalification, Long studentId) {
        Usuario user = servicioUsuarioDao.getUserById(studentId);
        Clase lesson = serviceLessonDao.getLessonById(dataCalification.getLessonId());
        Estado state = serviceStateDao.getStateById(1L);
        AlumnoClase studentLesson = serviceLessonDao.getStudentLesson(user, lesson);

        if (studentLesson.getCalification() == null) {
            Long id = serviceCalificationDao.create(dataCalification.getDescription(), dataCalification.getScore(), lesson, user);
            Calificacion calification = serviceCalificationDao.getCalificationById(id);
            serviceLessonDao.updateStudentLesson(studentLesson, calification);
        }
        List<Clase> lessons = serviceLessonDao.getLessonsByStateAndStudent(user, state);
        return lessons;
    }

    @Override
    public List<Clase> modifyLesson(DataLesson dataLesson, Long professorId) {

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

        lesson.setName(dataLesson.getName());

        Detalle detail = servicioDetalleDao.getById(lesson.getDetail().getIdDetail());

        detail.setCapacity(dataLesson.getCapacity());
        detail.setStartHour(hour_ini);
        detail.setEndHour(hour_fin);

        Disciplina discipline = servicioDisciplinaDao.get(dataLesson.getIdDiscipline());
        Dificultad difficulty = servicioDificultadDao.get(dataLesson.getIdDifficulty());
        Lugar place = servicePlaceDao.getPlaceById(dataLesson.getIdLugar());

        List<Clase> lessons;

        servicioDetalleDao.modify(detail);
        serviceLessonDao.modify(difficulty, discipline, place, date, lesson, user);
        lessons = serviceLessonDao.getLessonsByProfessor(user);
        return lessons;
    }

    @Override
    public DataLessonRegistration getLessonById(Long idLesson) {
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
        dataLesson.setName(lesson.getName());

        return dataLesson;
    }

    @Override
    public List<Clase> getAllAvailableLessons(Long studentId) {
        Usuario student = servicioUsuarioDao.getUserById(studentId);

        List<Clase> lessons = serviceLessonDao.getAllAvailableLessons(student);
        return lessons;
    }

    @Override
    public List<Clase> getLessonsByPreferences(Long userId) {

        Usuario alumno = servicioUsuarioDao.getUserById(userId);

        List<Clase> suggestedLessonsByPreferences = serviceLessonDao.getAllLessonsByPreferences(alumno);
        return suggestedLessonsByPreferences;
    }

    @Override
    public void assingLesson(Long idLesson, Long userId) {
        Clase lesson = serviceLessonDao.getLessonById(idLesson);
        Usuario student = servicioUsuarioDao.getUserById(userId);
        //TODO falta validar superposicion de horarios
        serviceLessonDao.assignLesson(lesson, student);
    }

    @Override
    public void changeLessonState(DataLesson dataLesson) {
        Long lessonId = dataLesson.getLessonId();
        Long stateId = dataLesson.getIdState();
        Clase lesson = serviceLessonDao.getLessonById(lessonId);
        Estado state = serviceStateDao.getStateById(stateId);
        serviceLessonDao.updateLessonState(lesson, state);
    }

    @Override
    public List<Clase> getLessonsByTaken(Long userId) {

        Usuario alumno = servicioUsuarioDao.getUserById(userId);

        List<Clase> suggestedLessonsByLessonsTaken = serviceLessonDao.getAllLessonsByLessonsTaken(alumno);
        return suggestedLessonsByLessonsTaken;
    }

}
