package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import ar.edu.unlam.tallerweb1.domain.user.ProfileService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    private LessonService lessonService;
    private LoginService loginService;
    private ProfileService profileService;

    @Autowired
    public ProfileController(LessonService lessonService, LoginService loginService, ProfileService profileService) {
        this.lessonService = lessonService;
        this.loginService = loginService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView goToProfile(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long userId = (Long) request.getSession().getAttribute("USER_ID");

        List<Disciplina> disciplines = lessonService.getPreferencesOrAllDisciplines(userId);

        model.addAttribute("disciplines", disciplines);
        model.addAttribute("savePreferences", new DataPreferencesRegistration());

        return new ModelAndView("profile", model);
    }

    @RequestMapping(path = "/getRole", method = RequestMethod.GET)
    public ModelAndView getRole(HttpServletRequest request) {
        Long userRole = (Long) request.getSession().getAttribute("ROLE");
        ModelMap model = new ModelMap();

        model.addAttribute("value", userRole);
        return new ModelAndView("value", model);
    }

    @RequestMapping(path = "/getUserData", method = RequestMethod.GET)
    public ModelAndView getUserData(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");

        Usuario user = profileService.getUserById(userId);

        ModelMap model = new ModelMap();
        model.addAttribute("user", user);

        return new ModelAndView("userData", model);
    }
}
