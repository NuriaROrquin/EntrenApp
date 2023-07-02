package ar.edu.unlam.tallerweb1.domain.lessons;


import ar.edu.unlam.tallerweb1.domain.clase.LessonServiceImpl;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServiceLessonTest {

    private LessonRepository serviceLessonDao;
    private UserRepository serviceUserDao;
    private DetailRepository servicioDetalleDao;
    private DisciplineRepository servicioDisciplinaDao;
    private DifficultyRepository servicioDificultadDao;
    private PlaceRepository servicePlaceDao;
    private StateRepository serviceStateDao;
    private HttpServletRequest request;
    private HttpSession sesion;
    private LessonServiceImpl classService;

    @Before
    public void init() {
        serviceLessonDao = mock(LessonRepository.class);
        serviceUserDao = mock(UserRepository.class);
        servicioDetalleDao = mock(DetailRepository.class);
        servicioDisciplinaDao = mock(DisciplineRepository.class);
        servicioDificultadDao = mock(DifficultyRepository.class);
        serviceStateDao = mock(StateRepository.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        classService = new LessonServiceImpl(this.serviceLessonDao, this.serviceUserDao, this.servicioDetalleDao, this.servicioDisciplinaDao, this.servicioDificultadDao, this.servicePlaceDao, this.serviceStateDao);
    }

    @Test
    public void getLessonsFromProfessor() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(3L, "profesor");
        Usuario professor = data.createUser(1L, "pabloantunez@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 30, 50, "Buenos Aires Club", "Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createClase(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2024, 11, 30), new Date(2025, 12, 25), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);

        List<Clase> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        lessonList.add(lesson2);
        when(serviceUserDao.getUserById(professor.getId())).thenReturn(professor);
        when(serviceLessonDao.getClassesByProfessorId(professor)).thenReturn(lessonList);
        List<Clase> lessonResult = classService.getLessonsByProfessorId(professor.getId());

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();
        assertThat(lessonResult).contains(lesson);
        assertThat(lessonResult).extracting("professor").contains(professor);

    }

    @Test
    public void getLessonsDependingStateFromProfessor() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createClase(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);

        when(serviceUserDao.getUserById(professor.getId())).thenReturn(professor);
        when(serviceLessonDao.getLessonsDependingStateFromProfessor(professor, state)).thenReturn(lessons);
        when(serviceStateDao.getStateById(state.getIdState())).thenReturn(state);
        List<Clase> lessonsResult = classService.getLessonsDependingStateFromProfessor(1L, 1L);

        assertThat(lessonsResult).isNotNull();
        assertThat(lessonsResult).isNotEmpty();
        assertThat(lessonsResult).extracting("professor").contains(professor);
        assertThat(lessonsResult).extracting("state").contains(state);
        verify(serviceUserDao, times(1)).getUserById(professor.getId());
        verify(serviceLessonDao, times(1)).getLessonsDependingStateFromProfessor(professor, state);
        verify(serviceStateDao, times(1)).getStateById(state.getIdState());

    }

    @Test
    public void whenIWantToCancelALessonByProfessorShouldChangeLessonsState() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createClase(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        Mockito.doNothing().when(serviceLessonDao).cancelLessonByProfessor(lesson, professor);
        when(serviceUserDao.getUserById(professor.getId())).thenReturn(professor);
        when(serviceLessonDao.getLessonById(lesson.getIdClass())).thenReturn(lesson);
        when(serviceLessonDao.getClassesByProfessorId(professor)).thenReturn(lessons);


        classService.cancelLesson(lesson.getIdClass(), professor.getId());
        verify(serviceLessonDao, times(1)).cancelLessonByProfessor(lesson, professor);
    }


    @Test
    public void getLessonsFromStudent() {
        BasicData data = new BasicData();

        Rol rol = data.createRole(2L, "alumno");
        Usuario user = data.createUser(1L, "norquin@gmail.com", "1234", "Nuri", rol, true);
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Descripcion", 10, 99);
        Detalle detail = data.createDetail(1L, LocalTime.of(8, 00), LocalTime.of(9, 00), 10);
        Lugar place = data.createPlace(1, 24, 28, "Descripcion", "Nombre");
        Estado state = data.createState(1, "PENDIENTE");
        Dificultad difficulty = data.createDifficulty(1, "FACIL");
        Clase lesson = data.createClase(new Date(2023, 06, 24), new Date(2023, 06, 24), null, detail, place, difficulty, discipline, user, state);
        Clase lesson2 = data.createClase(new Date(2024, 10, 30), new Date(2024, 10, 30), null, detail, place, difficulty, discipline, user, state);

        List<Clase> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        lessonList.add(lesson2);

        when(serviceUserDao.getUserById(user.getId())).thenReturn(user);
        when(serviceLessonDao.getClassesByIdAlumno(user)).thenReturn(lessonList);

        List<Clase> lessonResult = classService.getLessonsByStudentId(1L);

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();

    }



    /*// ------------------------------------------------- COMPLETAR TEST ---------------------------------------------------------

    public void whenIWantToCancelALessonByStudentShouldQuitStudent(){
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Finalizada");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Mockito.doNothing().when(serviceLessonDao).cancelLessonByProfessor(lesson, professor);
        classService.cancelLesson(lesson.getIdClass(), professor.getId());

    }*/
}
