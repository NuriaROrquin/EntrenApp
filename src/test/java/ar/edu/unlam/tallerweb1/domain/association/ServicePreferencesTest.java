package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonServiceImpl;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ServicePreferencesTest {
    private UserRepository userServiceDao;
    private DisciplineRepository disciplineServiceDao;
    private PreferencesRepository preferencesServiceDao;
    private HttpServletRequest request;
    private HttpSession session;
    private PreferencesServiceImpl preferencesService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userServiceDao = mock(UserRepository.class);
        disciplineServiceDao = mock(DisciplineRepository.class);
        preferencesServiceDao = mock(PreferencesRepository.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);

        preferencesService = new PreferencesServiceImpl(this.preferencesServiceDao, this.disciplineServiceDao, this.userServiceDao);
    }

    @Test
    public void whenIHaveThePreferencesShouldUpdateThePreferences() {
        DataPreferencesRegistration dataPreferencesRegistration = new DataPreferencesRegistration();

        List<Long> idDisciplines = new ArrayList<>();
        idDisciplines.add(1L);
        idDisciplines.add(2L);
        idDisciplines.add(3L);

        dataPreferencesRegistration.setIdDiscipline(idDisciplines);

        BasicData data = new BasicData();

        Rol studentRole = data.createRole(1L, "alumno");

        Usuario student = data.createUser(1L, "email@email.com", "123", "Nuri", studentRole, true, 50L);

        Disciplina discipline = data.createDiscipline(1L, "De agua");

        List<Disciplina> disciplines = new ArrayList<>();
        disciplines.add(discipline);

        Preferencias preferenceToDelete = data.createPreferences(1L, student, discipline);

        when(userServiceDao.getUserById(1L)).thenReturn(student);
        when(disciplineServiceDao.get(1L)).thenReturn(discipline);
        when(disciplineServiceDao.getAllTheDisciplines()).thenReturn(disciplines);
        when(preferencesServiceDao.getByDisciplineIdAndUserId(discipline.getIdDiscipline(), student.getId())).thenReturn(preferenceToDelete);

        preferencesService.savePreferences(dataPreferencesRegistration, student.getId());


        verify(userServiceDao).getUserById(anyLong());
        verify(disciplineServiceDao, times(3)).get(anyLong());
        verify(disciplineServiceDao).getAllTheDisciplines();
        verify(preferencesServiceDao, times(1)).getByDisciplineIdAndUserId(anyLong(), anyLong());
        verify(preferencesServiceDao, times(1)).delete(any(Preferencias.class));
        verify(preferencesServiceDao, times(1)).create(any(Usuario.class), any(Disciplina.class));
    }

}
