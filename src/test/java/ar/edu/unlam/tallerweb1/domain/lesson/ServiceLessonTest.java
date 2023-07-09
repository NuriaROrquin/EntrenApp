package ar.edu.unlam.tallerweb1.domain.lesson;


import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.delivery.models.*;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServiceLessonTest {

    private LessonRepository lessonServiceDao;
    private UserRepository userServiceDao;
    private DetailRepository detailServiceDao;
    private DisciplineRepository disciplineServiceDao;
    private DifficultyRepository difficultyServiceDao;
    private PlaceRepository placeServiceDao;
    private StateRepository stateServiceDao;
    private HttpServletRequest request;
    private HttpSession session;
    private LessonServiceImpl lessonService;
    private CalificationRepository calificationServiceDao;
    private PreferencesRepository preferencesServiceDao;
    @Spy
    private Detalle detailMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        lessonServiceDao = mock(LessonRepository.class);
        userServiceDao = mock(UserRepository.class);
        detailServiceDao = mock(DetailRepository.class);
        disciplineServiceDao = mock(DisciplineRepository.class);
        difficultyServiceDao = mock(DifficultyRepository.class);
        stateServiceDao = mock(StateRepository.class);
        calificationServiceDao = mock(CalificationRepository.class);
        preferencesServiceDao = mock(PreferencesRepository.class);

        placeServiceDao = mock(PlaceRepository.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);

        lessonService = new LessonServiceImpl(this.lessonServiceDao, this.userServiceDao, this.detailServiceDao, this.disciplineServiceDao, this.difficultyServiceDao, this.placeServiceDao, this.stateServiceDao, this.calificationServiceDao, this.preferencesServiceDao);
    }

    @Test
    public void whenIHaveTheDataLessonIShouldCreateANewLesson() {

        DataLessonRegistration dataLesson = new DataLessonRegistration();
        dataLesson.setAge_max(20);
        dataLesson.setAge_min(15);
        dataLesson.setCapacity(50);
        dataLesson.setDateStr("2023-07-04");
        dataLesson.setName("fútbol");
        dataLesson.setHour_finString("20:00:00");
        dataLesson.setHour_iniString("21:00:00");
        dataLesson.setIdDifficulty(1L);
        dataLesson.setIdDiscipline(2L);
        dataLesson.setIdLugar(1L);

        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(3L, "profesor");
        Usuario professor = data.createUser(1L, "pabloantunez@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Disciplina discipline = data.createDiscipline(dataLesson.getIdDiscipline(), "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(dataLesson.getHour_ini());
        LocalTime endTime = data.setHourMinutes(dataLesson.getHour_fin());
        Detalle detailmock = mock(Detalle.class);
        Detalle detail = data.createDetail(detailmock.getIdDetail(), startTime, endTime, dataLesson.getCapacity());
        Lugar place = data.createPlace(dataLesson.getIdLugar(), 30, 50, "Buenos Aires Club", "Buenos Aires");
        Dificultad difficulty = data.createDifficulty(dataLesson.getIdDifficulty(), "Avanzado");
        Estado state = data.createState(1L, "Pendiente");

        Date date = new Date(123, 6, 4);
        Mockito.doNothing().when(lessonServiceDao).create(difficulty, detail, discipline, place, date, professor, 10, 15, "Natacion", state);
        when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
        when(stateServiceDao.getStateById(state.getIdState())).thenReturn(state);
        when(disciplineServiceDao.get(discipline.getIdDiscipline())).thenReturn(discipline);
        when(difficultyServiceDao.get(difficulty.getIdDifficulty())).thenReturn(difficulty);
        when(placeServiceDao.getPlaceById(place.getIdPlace())).thenReturn(place);
        when(detailServiceDao.getById(detail.getIdDetail())).thenReturn(detail);

        lessonService.registerLesson(dataLesson, professor.getId());
        verify(lessonServiceDao, times(1)).create(difficulty, detail, discipline, place, date, professor, dataLesson.getAge_min(), dataLesson.getAge_max(), dataLesson.getName(), state);

    }

    @Test
    public void getLessonsFromProfessor() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(3L, "profesor");
        Usuario professor = data.createUser(1L, "pabloantunez@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 30, 50, "Buenos Aires Club", "Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2024, 11, 30), new Date(2025, 12, 25), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        List<Clase> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        lessonList.add(lesson2);
        when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
        when(lessonServiceDao.getLessonsByProfessor(professor)).thenReturn(lessonList);
        List<Clase> lessonResult = lessonService.getLessonsByProfessorId(professor.getId());

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();
        assertThat(lessonResult).contains(lesson);
        assertThat(lessonResult).extracting("professor").contains(professor);

    }

    @Test
    public void getLessonsByStateFromProfessor() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);

        when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
        when(lessonServiceDao.getLessonsByStateAndProfessor(professor, state)).thenReturn(lessons);
        when(stateServiceDao.getStateById(state.getIdState())).thenReturn(state);
        List<Clase> lessonsResult = lessonService.getLessonsByState(1L, 1L);

        assertThat(lessonsResult).isNotNull();
        assertThat(lessonsResult).isNotEmpty();
        assertThat(lessonsResult).extracting("professor").contains(professor);
        assertThat(lessonsResult).extracting("state").contains(state);
        verify(userServiceDao, times(1)).getUserById(professor.getId());
        verify(lessonServiceDao, times(1)).getLessonsByStateAndProfessor(professor, state);
        verify(stateServiceDao, times(1)).getStateById(state.getIdState());

    }

    @Test
    public void whenIWantToCancelALessonByProfessorShouldChangeLessonsState() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        Mockito.doNothing().when(lessonServiceDao).cancelLessonByProfessor(lesson, professor);
        when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
        when(lessonServiceDao.getLessonById(lesson.getIdClass())).thenReturn(lesson);
        when(lessonServiceDao.getLessonsByProfessor(professor)).thenReturn(lessons);


        lessonService.cancelLesson(lesson.getIdClass(), professor.getId());
        verify(lessonServiceDao, times(1)).cancelLessonByProfessor(lesson, professor);
    }

    @Test
    public void getLessonsFromStudent() {
        BasicData data = new BasicData();

        Rol rol = data.createRole(2L, "alumno");
        Usuario user = data.createUser(1L, "norquin@gmail.com", "1234", "Nuri", rol, true);
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        Detalle detail = data.createDetail(1L, LocalTime.of(8, 00), LocalTime.of(9, 00), 10);
        Lugar place = data.createPlace(1, 24, 28, "Descripcion", "Nombre");
        Estado state = data.createState(1, "PENDIENTE");
        Dificultad difficulty = data.createDifficulty(1, "FACIL");
        Clase lesson = data.createLesson(new Date(2023, 06, 24), new Date(2023, 06, 24), null, detail, place, difficulty, discipline, user, state, "Natacion", 10, 99);
        Clase lesson2 = data.createLesson(new Date(2024, 10, 30), new Date(2024, 10, 30), null, detail, place, difficulty, discipline, user, state, "Natacion", 10, 99);

        List<Clase> expectedLessons = new ArrayList<>();
        expectedLessons.add(lesson);
        expectedLessons.add(lesson2);

        when(userServiceDao.getUserById(user.getId())).thenReturn(user);
        when(lessonServiceDao.getLessonsByStudent(user)).thenReturn(expectedLessons);

        List<Clase> lessonResult = lessonService.getLessonsByStudentId(1L);

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();

    }

    @Test
    public void getLessonsByStateFromStudent() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);

        Rol roleStudent = data.createRole(1L, "alumno");
        Usuario student = data.createUser(1L, "nuri@hotmail.com", "1234", "Nuri", roleStudent, true);


        when(userServiceDao.getUserById(student.getId())).thenReturn(student);
        when(stateServiceDao.getStateById(state.getIdState())).thenReturn(state);
        when(lessonServiceDao.getLessonsByStateAndStudent(student, state)).thenReturn(lessons);
        List<Clase> lessonsResult = lessonService.getLessonsByState(1L, state.getIdState());

        assertThat(lessonsResult).isNotNull();
        assertThat(lessonsResult).isNotEmpty();
        assertThat(lessonsResult).extracting("state").contains(state);
        verify(userServiceDao, times(1)).getUserById(student.getId());
        verify(lessonServiceDao, times(1)).getLessonsByStateAndStudent(student, state);
        verify(stateServiceDao, times(1)).getStateById(state.getIdState());
    }

    @Test
    public void whenIWantToCalificateALessonShouldAddCalificationToIt() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Juan", roleStudent, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Calificacion calification = data.createCalification(1L, "La mejor clase!", 5, student, lesson);


        List<Clase> studentLessons = new ArrayList<>();
        studentLessons.add(lesson);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);

        DataCalification dataCalification = new DataCalification();
        String description = "Hola";
        int score = 3;
        dataCalification.setDescription(description);
        dataCalification.setScore(score);

        when(userServiceDao.getUserById(student.getId())).thenReturn(student);
        when(lessonServiceDao.getLessonById(lesson.getIdClass())).thenReturn(lesson);
        when(calificationServiceDao.create(description, score, lesson, student)).thenReturn(calification);
        when(lessonServiceDao.getLessonsByStudent(student)).thenReturn(studentLessons);
        Mockito.doNothing().when(lessonServiceDao).calificateLessonByStudent(lesson, calification, student);

        List<Clase> lessonsResult = lessonService.calificateLessonByStudent(lesson.getIdClass(), dataCalification, student.getId());

        assertThat(lessonsResult).isNotNull();
        verify(userServiceDao, times(1)).getUserById(student.getId());
        verify(lessonServiceDao, times(1)).getLessonById(lesson.getIdClass());
        verify(calificationServiceDao, times(1)).create(description, score, lesson, student);
        verify(lessonServiceDao, times(1)).getLessonsByStudent(student);

    }

    @Test
    public void whenIWantToModifyALessonShouldChangeTheirInformation() throws ParseException {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);


        DataLesson dataLesson = new DataLesson();

        dataLesson.setDate("2023-11-05");
        dataLesson.setCapacity(lesson.getDetail().getCapacity());
        dataLesson.setHour_iniString(lesson.getDetail().getStartHour().toString());
        dataLesson.setHour_finString(lesson.getDetail().getEndHour().toString());
        dataLesson.setIdDifficulty(lesson.getDifficulty().getIdDifficulty());
        dataLesson.setIdDiscipline(lesson.getDiscipline().getIdDiscipline());
        dataLesson.setIdLugar(lesson.getPlace().getIdPlace());
        dataLesson.setLessonId(1L);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2023-11-05");

        when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
        when(lessonServiceDao.getLessonById(1L)).thenReturn(lesson);
        when(lessonServiceDao.getLessonsByProfessor(professor)).thenReturn(lessons);
        when(disciplineServiceDao.get(discipline.getIdDiscipline())).thenReturn(discipline);
        when(difficultyServiceDao.get(difficulty.getIdDifficulty())).thenReturn(difficulty);
        when(placeServiceDao.getPlaceById(place.getIdPlace())).thenReturn(place);
        when(detailServiceDao.getById(lesson.getDetail().getIdDetail())).thenReturn(detail);

        Mockito.doNothing().when(lessonServiceDao).modify(difficulty, discipline, place, date, lesson, professor);
        Mockito.doNothing().when(detailServiceDao).modify(detail);

        lessonService.modifyLesson(dataLesson, professor.getId());

        verify(lessonServiceDao, times(1)).modify(difficulty, discipline, place, date, lesson, professor);

    }

    @Test
    public void whenIGiveLessonIdShouldBringTheLesson() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        DataLessonRegistration dataLesson = new DataLessonRegistration();

        dataLesson.setName(lesson.getName());
        dataLesson.setDate(lesson.getDate());
        dataLesson.setCapacity(lesson.getDetail().getCapacity());
        dataLesson.setAge_max(lesson.getMaximum_age());
        dataLesson.setAge_min(lesson.getMinimum_age());
        dataLesson.setIdDifficulty(lesson.getDifficulty().getIdDifficulty());
        dataLesson.setIdDiscipline(lesson.getDiscipline().getIdDiscipline());
        dataLesson.setIdLugar(lesson.getPlace().getIdPlace());

        when(lessonServiceDao.getLessonById(1L)).thenReturn(lesson);

        DataLessonRegistration lessonResult = lessonService.getLessonById(1L);

        verify(lessonServiceDao, times(1)).getLessonById(1L);
        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult.getName()).isEqualTo(dataLesson.getName());

    }

    @Test
    public void whenIWantToKnowAllTheAvailablesClassesForMe() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "santiago.opera@gmail.com", "unlam", "Santiago", roleProfessor, true);
        Lugar place = data.createPlace(1L, 3456894518L, 7896548548L, "Un lugar preparado para vos", "Plaza Sere");
        Dificultad difficulty = data.createDifficulty(1L, "Principiante");
        Disciplina discipline = data.createDiscipline(1L, "Funcional");
        LocalTime startTime = data.setHourMinutes(14, 30);
        LocalTime endTime = data.setHourMinutes(15, 45);
        Detalle detail = data.createDetail(1L, startTime, endTime, 7);
        Estado state = data.createState(1L, "PENDIENTE");

        Clase lesson = data.createLesson(new Date(2023, 7, 01), new Date(2023, 7, 01), new Date(2023, 9, 01), detail, place, difficulty, discipline, professor, state, "Natacion", 16, 55);
        Clase lesson2 = data.createLesson(new Date(2023, 7, 01), new Date(2023, 8, 01), new Date(2023, 10, 01), detail, place, difficulty, discipline, professor, state, "Natacion", 16, 55);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);


        Rol studentRole = data.createRole(1l, "alumno");
        Usuario student = data.createUser(1L, "facundo.fagnano@gmail.com", "AguanteElRojo", "Facundo", studentRole, true);

        when(userServiceDao.getUserById(student.getId())).thenReturn(student);
        when(lessonServiceDao.getAllAvailableLessons(student)).thenReturn(lessons); // firma que tiene el metodo en el repo
        List<Clase> lessonsResult = lessonService.getAllAvailableLessons(student.getId()); // firma que va a tener el metodo en el serviceLessonImpl

        assertThat(lessonsResult).isNotNull();
        assertThat(lessonsResult).isNotEmpty();
        assertThat(lessons).hasSize(2);
        assertThat(lessonsResult).contains(lesson);

    }

    @Test
    public void whenIWantToKnowPreferencesAndPreferenceHasDisciplinesShouldAppearPreferredDisciplines(){

        BasicData data = new BasicData();
        Disciplina discipline = data.createDiscipline(1L, "Deporte Individual");
        Disciplina discipline2 = data.createDiscipline(2L, "Deporte Grupal");

        List<Disciplina> disciplineList = new ArrayList<>();
        disciplineList.add(discipline);
        disciplineList.add(discipline2);

        Rol studentRole = data.createRole(2L, "alumno");
        Usuario student = data.createUser(2L, "facundo.fagnano@gmail.com", "AguanteElRojo", "Facundo", studentRole, true);

        when(preferencesServiceDao.getPreferredDisciplinesById(student.getId())).thenReturn(disciplineList);
        List<Disciplina> disciplinesResult = lessonService.getPreferencesOrAllDisciplines(student.getId());

        assertThat(disciplinesResult).isNotNull();
        assertThat(disciplinesResult).isNotEmpty();
        assertThat(disciplineList).hasSize(2);
        assertThat(disciplinesResult).isEqualTo(disciplineList);

    }

    @Test
    public void whenIWantToKnowPreferencesAndPreferenceDoesntHaveDisciplinesShouldAppearAllDisciplines(){

        BasicData data = new BasicData();
        Disciplina discipline = data.createDiscipline(1L, "De agua");
        Disciplina discipline2 = data.createDiscipline(2L, "De cancha");
        Disciplina discipline3 = data.createDiscipline(2L, "De combate");
        Disciplina discipline4 = data.createDiscipline(2L, "Acrobatica");

        List<Disciplina> disciplineList = new ArrayList<>();
        disciplineList.add(discipline);
        disciplineList.add(discipline2);
        disciplineList.add(discipline3);
        disciplineList.add(discipline4);

        Rol studentRole = data.createRole(2L, "alumno");
        Usuario student = data.createUser(2L, "facundo.fagnano@gmail.com", "AguanteElRojo", "Facundo", studentRole, true);

        when(preferencesServiceDao.getPreferredDisciplinesById(student.getId())).thenReturn(null);
        when(disciplineServiceDao.getAllTheDisciplines()).thenReturn(disciplineList);
        List<Disciplina> disciplinesResult = lessonService.getPreferencesOrAllDisciplines(student.getId());

        assertThat(disciplinesResult).isNotNull();
        assertThat(disciplinesResult).isNotEmpty();
        assertThat(disciplineList).hasSize(4);
        assertThat(disciplinesResult).isEqualTo(disciplineList);
    }
    /*// ------------------------------------------------- COMPLETAR TEST ---------------------------------------------------------

    public void whenIWantToCancelALessonByStudentShouldQuitStudent(){
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Deporte Acuatico", "Natacion", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Finalizada");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Mockito.doNothing().when(serviceLessonDao).cancelLessonByProfessor(lesson, professor);
        classService.cancelLesson(lesson.getIdClass(), professor.getId());

    }*/
}
