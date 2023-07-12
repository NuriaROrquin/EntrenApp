package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    private LessonService lessonService;
    private LoginService loginService;

    @Autowired
    public ProfileController(LessonService lessonService, LoginService loginService) {
        this.lessonService = lessonService;
        this.loginService = loginService;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView goToProfile(HttpServletRequest request) {
        return new ModelAndView("profile");
    }
}
