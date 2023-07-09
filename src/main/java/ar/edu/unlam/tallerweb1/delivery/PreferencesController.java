package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.association.PreferencesService;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PreferencesController {

    private PreferencesService preferencesService;
    private LessonService lessonService;

    @Autowired
    public PreferencesController(PreferencesService preferencesService, LessonService lessonService) {
        this.preferencesService = preferencesService;
        this.lessonService = lessonService;
    }

    @RequestMapping("/preferences")
    public ModelAndView goToPreferences(HttpServletRequest request) {

        ModelMap model = new ModelMap();

        Long userId = (Long) request.getSession().getAttribute("USER_ID");

        List<Disciplina> disciplines = lessonService.getPreferencesOrAllDisciplines(userId);
        
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("savePreferences", new DataPreferencesRegistration());

        return new ModelAndView("formsPreferences", model);
    }


    @RequestMapping(value = "/validate-preferences", method = RequestMethod.POST)
    public ModelAndView validate(@ModelAttribute("savePreferences") DataPreferencesRegistration dataPreferencesRegistration, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idUser = (Long) request.getSession().getAttribute("USER_ID");
        preferencesService.savePreferences(dataPreferencesRegistration, idUser);

        model.put("preferencesSaved", "Las preferencias se han guardado correctamente");

        return new ModelAndView("preferences", model);
    }

}