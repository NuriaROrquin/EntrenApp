package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.association.PreferencesServiceImpl;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ServicePreferencesTest {

    private PreferencesServiceImpl preferencesService;
    private PreferencesRepository preferencesServiceDao;
    private UserRepository userServiceDao;
    private DisciplineRepository disciplineServiceDao;
    private HttpServletRequest request;
    private HttpSession session;



    @Before
    public void init() {
        preferencesServiceDao = mock(PreferencesRepository.class);
        userServiceDao = mock(UserRepository.class);
        disciplineServiceDao = mock(DisciplineRepository.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);

        preferencesService = new PreferencesServiceImpl(this.preferencesServiceDao,this.disciplineServiceDao,this.userServiceDao);
    }


    @Test
    public void whenIHaveTheListOfPreferencesOfTheUserShouldSaveIt() {

        DataPreferencesRegistration dataPreferencesRegistration = new DataPreferencesRegistration();

        BasicData data = new BasicData();

        Disciplina futbol = data.createDiscipline(1L,"Fútbol 5", "Fútbol en cancha de 5", 10, 15);
        Disciplina basquet = data.createDiscipline(2L, "Básquet", "El deporte de negros", 20, 25);
        Disciplina criquet = data.createDiscipline(3L, "Criquet", "Quién conoce este deporte y cómo puede ser el segundo más popular del mundo", 3, 7);
        Disciplina rugby = data.createDiscipline(4L, "Rugby", "Guarda que te tackleo hasta en el boliche", 1, 65);

        List<Long> disciplinesToAdd = new ArrayList<>();
        disciplinesToAdd.add(futbol.getIdDiscipline());
        disciplinesToAdd.add(basquet.getIdDiscipline());
        disciplinesToAdd.add(criquet.getIdDiscipline());
        disciplinesToAdd.add(rugby.getIdDiscipline());

        dataPreferencesRegistration.setIdDiscipline(disciplinesToAdd);

        Rol role = data.createRole(2L, "Alumno");
        Usuario user = data.createUser(1L, "alumno@alumno.com", "1234", "Alumno", role, true);

        List<Disciplina> disciplines = new ArrayList<>();
        disciplines.add(futbol);
        disciplines.add(basquet);
        disciplines.add(criquet);
        disciplines.add(rugby);

        Mockito.doNothing().when(preferencesServiceDao).create(user, futbol);
        Mockito.doNothing().when(preferencesServiceDao).create(user, basquet);
        Mockito.doNothing().when(preferencesServiceDao).create(user, criquet);
        Mockito.doNothing().when(preferencesServiceDao).create(user, rugby);
        when(userServiceDao.getUserById(user.getId())).thenReturn(user);
        when(disciplineServiceDao.get(futbol.getIdDiscipline())).thenReturn(futbol);
        when(disciplineServiceDao.get(basquet.getIdDiscipline())).thenReturn(basquet);
        when(disciplineServiceDao.get(criquet.getIdDiscipline())).thenReturn(criquet);
        when(disciplineServiceDao.get(rugby.getIdDiscipline())).thenReturn(rugby);

        preferencesService.savePreferences(dataPreferencesRegistration, user.getId());
        verify(preferencesServiceDao, times(1)).create(user, futbol);
        verify(preferencesServiceDao, times(1)).create(user, basquet);
        verify(preferencesServiceDao, times(1)).create(user, criquet);
        verify(preferencesServiceDao, times(1)).create(user, rugby);

    }

}
