package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private LessonService lessonService;
    private LoginService loginService;

    @Autowired
    public HomeController(LessonService lessonService, LoginService loginService) {
        this.lessonService = lessonService;
        this.loginService = loginService;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView goToHome(HttpServletRequest request) {

        ModelAndView model;

        if ((long) request.getSession().getAttribute("ROLE") == 2) {
            
            model = new ModelAndView("studentHome");
        } else {

            model = new ModelAndView("professorHome");
        }

        return model;
    }


}
