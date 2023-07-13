package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.association.PreferencesService;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import ar.edu.unlam.tallerweb1.domain.user.ProfileService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfileControllerTest {
    private ProfileController profileController;
    private PreferencesService preferencesService;
    private LessonService lessonService;
    private LoginService loginService;
    private ProfileService profileService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init(){
        preferencesService = mock(PreferencesService.class);
        lessonService = mock(LessonService.class);
        loginService = mock(LoginService.class);
        profileService = mock(ProfileService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        profileController = new ProfileController(lessonService, loginService, profileService);
    }
    @Test
    public void whenTheUserWantToSeeTheSuggestedLessonsItShouldAppear(){

        BasicData basicData = new BasicData();
        Disciplina disciplineOne = basicData.createDiscipline(1L,"Deporte Acuatico");
        Disciplina disciplineTwo = basicData.createDiscipline(2L,"Deporte Individual");
        Rol studentRole = basicData.createRole(2L,"alumno");
        Usuario alumno = basicData.createUser(4L,"alumno@unlam.com","1234","Facundo", studentRole, true, 50L);

        List<Disciplina> disciplines = new ArrayList<>();
        disciplines.add(disciplineOne);
        disciplines.add(disciplineTwo);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(alumno.getId());
        when(lessonService.getPreferencesOrAllDisciplines(alumno.getId())).thenReturn(disciplines);
        ModelAndView view = profileController.goToProfile(request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("formsPreferences");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }


}
