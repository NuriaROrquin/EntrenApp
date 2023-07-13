package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonServiceImpl;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class CalificationServiceTest {
   private CalificationService calificationService;
   private UserRepository userServiceDao;
   private CalificationRepository calificationServiceDao;
   private HttpServletRequest request;
   private HttpSession session;
   private LessonServiceImpl lessonService;

   @Before
   public void init() {
      MockitoAnnotations.initMocks(this);
      userServiceDao = mock(UserRepository.class);
      calificationServiceDao = mock(CalificationRepository.class);
      session = mock(HttpSession.class);
      request = mock(HttpServletRequest.class);

      calificationService = new CalificationServiceImpl(calificationServiceDao,userServiceDao);
   }
   @Test
   @Transactional
   @Rollback
   public void whenINeedProfessorCalificationsShouldBringTheirCalifications(){
      BasicData data = new BasicData();
      Rol roleProfessor = data.createRole(1L, "profesor");
      Rol roleStudent = data.createRole(2L, "alumno");

      Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
      Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

      Lugar place = data.createPlace( 34615743.0, 58503336.0,"Club Buenos Aires");
      Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
      Disciplina discipline = data.createDiscipline(1L, "Crossfit");
      LocalTime startTime = data.setHourMinutes(2, 30);
      LocalTime endTime = data.setHourMinutes(4, 00);
      Detalle detail = data.createDetail(1L, startTime, endTime, 50);
      Estado state = data.createState(1L, "Pendiente");

      Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson2 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson4 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson5 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);

      lesson.setIdClass(1L);
      lesson2.setIdClass(2L);
      lesson3.setIdClass(3L);
      lesson4.setIdClass(4L);
      lesson5.setIdClass(5L);

      Calificacion calification = data.createCalification(1L, "La mejor clase", 5, student, lesson);
      Calificacion calification2 = data.createCalification(2L, "Medio", 3, student, lesson2);
      Calificacion calification3 = data.createCalification(3L, "Mala", 4, student, lesson3);
      Calificacion calification4 = data.createCalification(4L, "Mala", 4, student, lesson4);
      Calificacion calification5 = data.createCalification(5L, "Mala", 2, student, lesson5);

      List<Calificacion> professorCalifications = new ArrayList<>();
      professorCalifications.add(calification);
      professorCalifications.add(calification2);
      professorCalifications.add(calification3);
      professorCalifications.add(calification4);
      professorCalifications.add(calification5);

      AlumnoClase studentLesson = data.createAlumnoClase(1,student,lesson,calification);
      AlumnoClase studentLesson2 = data.createAlumnoClase(1,student,lesson2,calification2);
      AlumnoClase studentLesson3 = data.createAlumnoClase(1,student,lesson3,calification3);
      AlumnoClase studentLesson4 = data.createAlumnoClase(1,student,lesson4,calification4);
      AlumnoClase studentLesson5 = data.createAlumnoClase(1,student,lesson5,calification5);
      Integer limit = 5;

      when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
      when(calificationServiceDao.getProfessorCalificationsDao(professor,limit)).thenReturn(professorCalifications);
      List<Calificacion> calificationsResult = calificationService.getProfessorCalifications(professor.getId(), limit);

      verify(userServiceDao, times(1)).getUserById(professor.getId());
      verify(calificationServiceDao, times(1)).getProfessorCalificationsDao(professor,limit);


   }
   @Test
   @Transactional
   @Rollback
   public void whenINeedProfessorCalificationsShouldBringHisAverage(){
      BasicData data = new BasicData();
      Rol roleProfessor = data.createRole(1L, "profesor");
      Rol roleStudent = data.createRole(2L, "alumno");

      Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
      Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

      Lugar place = data.createPlace(34615743.0, 58503336.0,  "Club Buenos Aires");
      Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
      Disciplina discipline = data.createDiscipline(1L, "Crossfit");
      LocalTime startTime = data.setHourMinutes(2, 30);
      LocalTime endTime = data.setHourMinutes(4, 00);
      Detalle detail = data.createDetail(1L, startTime, endTime, 50);
      Estado state = data.createState(1L, "Pendiente");

      Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson2 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson4 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
      Clase lesson5 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);

      lesson.setIdClass(1L);
      lesson2.setIdClass(2L);
      lesson3.setIdClass(3L);
      lesson4.setIdClass(4L);
      lesson5.setIdClass(5L);

      Calificacion calification = data.createCalification(1L, "La mejor clase", 5, student, lesson);
      Calificacion calification2 = data.createCalification(2L, "Medio", 3, student, lesson2);
      Calificacion calification3 = data.createCalification(3L, "Mala", 4, student, lesson3);
      Calificacion calification4 = data.createCalification(4L, "Mala", 2, student, lesson4);
      Calificacion calification5 = data.createCalification(5L, "Mala", 1, student, lesson5);

      List<Calificacion> professorCalifications = new ArrayList<>();
      professorCalifications.add(calification);
      professorCalifications.add(calification2);
      professorCalifications.add(calification3);
      professorCalifications.add(calification4);
      professorCalifications.add(calification5);

      AlumnoClase studentLesson = data.createAlumnoClase(1,student,lesson,calification);
      AlumnoClase studentLesson2 = data.createAlumnoClase(1,student,lesson2,calification2);
      AlumnoClase studentLesson3 = data.createAlumnoClase(1,student,lesson3,calification3);
      AlumnoClase studentLesson4 = data.createAlumnoClase(1,student,lesson4,calification4);
      AlumnoClase studentLesson5 = data.createAlumnoClase(1,student,lesson5,calification5);
      Double average = 3.0;

      when(userServiceDao.getUserById(professor.getId())).thenReturn(professor);
      when(calificationServiceDao.getProfessorAverage(professor)).thenReturn(average);
      Double averageResult = calificationService.getProfessorCalificationsAverage(professor.getId());

      verify(userServiceDao,times(1)).getUserById(professor.getId());
      verify(calificationServiceDao,times(1)).getProfessorAverage(professor);

   }
}
