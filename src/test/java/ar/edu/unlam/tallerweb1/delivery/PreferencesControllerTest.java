package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.*;
import ar.edu.unlam.tallerweb1.domain.association.PreferencesService;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PreferencesControllerTest {

    private PreferencesController preferencesController;
    private PreferencesService preferencesService;
    private LessonController lessonController;
    private LessonService lessonService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init() {
        lessonService = mock(LessonService.class);
        lessonController = new LessonController(this.lessonService);
        preferencesService = mock(PreferencesService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        preferencesController = new PreferencesController(this.preferencesService, this.lessonService);

    }

    @Test
    public void whenAUserWantsToLogAPreferenceShouldShowThePreferencesList() {

        ModelAndView view = preferencesController.goToPreferences();

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("formsPreferences");

    }

    @Test
    public void WhenAUserSelectThePreferencesShouldSaveIt(){

        BasicData data = new BasicData();

        Rol role = data.createRole(2L,"Alumno");
        Usuario user = data.createUser(1L,"facundofagnano@gmail.com", "1234", "Facundo", role, true);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(user.getId());

        ModelAndView view = preferencesController.validate(new DataPreferencesRegistration(), request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("preferences");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
    }
}
