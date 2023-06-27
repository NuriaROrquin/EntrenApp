package ar.edu.unlam.tallerweb1.domain.lessons;


import ar.edu.unlam.tallerweb1.domain.clase.LessonServiceImpl;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceClassTest {

    private LessonRepository lessonRepository;
    private RepositorioUsuario userRepository;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;
    private PlaceRepository servicePlaceDao;
    private StateRepository serviceStateDao;
    private HttpServletRequest request;
    private HttpSession sesion;
    private LessonServiceImpl classService;

    @Before
    public void init() {
        lessonRepository = mock(LessonRepository.class);
        userRepository = mock(RepositorioUsuario.class);
        servicioDetalleDao = mock(RepositorioDetalle.class);
        servicioDisciplinaDao = mock(RepositorioDisciplina.class);
        servicioDificultadDao = mock(RepositorioDificultad.class);
        serviceStateDao = mock(StateRepository.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        classService = new LessonServiceImpl(this.lessonRepository, this.userRepository, this.servicioDetalleDao, this.servicioDisciplinaDao, this.servicioDificultadDao, this.servicePlaceDao, this.serviceStateDao);
    }

    @Test
    public void getLessonsFromProfessor(){

        // Rol Profesor
        Rol roleProfessor = new Rol();
        roleProfessor.setIdRole(3L);
        roleProfessor.setDescription("profesor");

        String email = "pabloantunez@otmial.com";
        String password = "1234";

        // Profesor
        Usuario professor = new Usuario();
        professor.setId(1L);
        professor.setEmail(email);
        professor.setPassword(password);
        professor.setRol(roleProfessor);

        //disciplina
        Disciplina discipline = new Disciplina();
        discipline.setName("Crossfit");

        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(LocalTime.of(8, 00));
        detail.setEndHour(LocalTime.of(9, 00));

        //clase1
        Clase lesson = new Clase();
        lesson.setIdClass(1);
        lesson.setDiscipline(discipline);
        lesson.setDate(new Date(2023, 06, 24));
        lesson.setDetail(detail);
        lesson.setProfesor(professor);

        //clase2
        Clase lesson2 = new Clase();
        lesson2.setIdClass(1);
        lesson2.setDiscipline(discipline);
        lesson2.setDate(new Date(2024, 10, 30));
        lesson2.setDetail(detail);
        lesson2.setProfesor(professor);

        //lugar
        Lugar place = new Lugar();
        place.setIdPlace(1L);

        List<Clase> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        lessonList.add(lesson2);
        when(userRepository.getUserById(professor.getId())).thenReturn(professor);
        when(lessonRepository.getClassesByProfessorId(professor)).thenReturn(lessonList);
        List<Clase> lessonResult = classService.getLessonsByProfessorId(professor.getId());

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();
        assertThat(lessonResult).contains(lesson);
        assertThat(lessonResult).extracting("professor").contains(professor);

    }

    @Test
    public void getLessonsInStateFinishedFromProfessor(){
        // Rol
        BasicData dataRole = new BasicData();
        Rol role = dataRole.createRole(1L, "profesor");

        // Profesor
        BasicData dataUser = new BasicData();
        Usuario professor = dataUser.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);

        // Lugar
        BasicData dataPlace = new BasicData();
        Lugar place = dataPlace.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");

        // Dificultad
        BasicData dataDifficulty = new BasicData();
        Dificultad difficulty = dataDifficulty.createDifficulty(1L, "Avanzado");


        // Disciplina
        BasicData dataDiscipline = new BasicData();
        Disciplina discipline = dataDiscipline.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);


        // Detalle
        BasicData dataDetail = new BasicData();
        BasicData detailStartHour = new BasicData();
        BasicData detailEndHour = new BasicData();
        LocalTime startTime = detailStartHour.setHourMinutes(2,30);
        LocalTime endTime = detailEndHour.setHourMinutes(4,00);
        Detalle detail = dataDetail.createDetail(1L,startTime,endTime,50 );

        // Estado
        BasicData dataState = new BasicData();
        Estado state = dataState.createState(1L,"Finalizada");

        // Estado 2
        BasicData dataState2 = new BasicData();
        Estado state2 = dataState2.createState(2L,"Cancelada");

        // Clase 1
        BasicData dataLesson = new BasicData();
        Clase lesson = dataLesson.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        // Clase 2
        BasicData dataLesson2 = new BasicData();
        Clase lesson2 = dataLesson2.createClase(1,new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

        // Clase 3
        BasicData dataLesson3 = new BasicData();
        Clase lesson3 = dataLesson3.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state2);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);
        lessons.add(lesson3);

        when(userRepository.getUserById(professor.getId())).thenReturn(professor);
        when(lessonRepository.getClassesByProfessorId(professor)).thenReturn(lessons);
        when(serviceStateDao.getStateById(state.getIdState())).thenReturn(state);
        List<Clase> lessonsResult = classService.getLessonsInStateFinishedFromProfessor(professor.getId(),state.getIdState());

        assertThat(lessonsResult).isNotNull();
        assertThat(lessonsResult).isNotEmpty();
    }


}
