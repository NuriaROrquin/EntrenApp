package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferences;
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
    public PreferencesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @RequestMapping("/preferences")
    public ModelAndView goToPreferences() {

        ModelMap model = new ModelMap();

        List<Disciplina> disciplines = lessonService.getAllDisciplines();
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("savePreferences", new DataPreferences());

        return new ModelAndView("formsPreferences", model);
    }


    @RequestMapping(value = "/validate-preferences", method = RequestMethod.POST)
    public ModelAndView validate(@ModelAttribute("savePreferences") DataPreferences dataPreferences, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idUser = (Long) request.getSession().getAttribute("USER_ID");
        preferencesService.savePreferences(dataPreferences, idUser);

        return new ModelAndView("studentHome");
    }

}