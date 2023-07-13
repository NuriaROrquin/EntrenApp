package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.association.PreferencesService;
import ar.edu.unlam.tallerweb1.domain.association.ServicePreferencesTest;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
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

public class PreferencesControllerTest {
    private PreferencesController preferencesController;
    private PreferencesService preferencesService;
    private LessonService lessonService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init(){
        preferencesService = mock(PreferencesService.class);
        lessonService = mock(LessonService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        preferencesController = new PreferencesController(preferencesService, lessonService);
    }

    @Test
    public void whenTheUserSendPreferencesShouldSendPreferencesToService(){

        BasicData basicData = new BasicData();
        Rol studentRole = basicData.createRole(2L,"alumno");
        Usuario alumno = basicData.createUser(4L,"alumno@unlam.com","1234","Facundo", studentRole, true, 50L);


        List<Long> preferencesIds = new ArrayList<>();
        preferencesIds.add(1L);
        preferencesIds.add(2L);
        preferencesIds.add(3L);

        DataPreferencesRegistration dataPreferencesRegistration = new DataPreferencesRegistration();

        dataPreferencesRegistration.setIdDiscipline(preferencesIds);

        Map<String, Object> expectedModel = new ModelMap();

        expectedModel.put("preferencesSaved", "Las preferencias se han guardado correctamente");


        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(alumno.getId());
        Mockito.doNothing().when(preferencesService).savePreferences(dataPreferencesRegistration, alumno.getId());

        ModelAndView view = preferencesController.validate(dataPreferencesRegistration, request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("preferences");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getModel()).isEqualTo(expectedModel);

    }


}
