package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.association.StudentLessonService;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    private LessonService lessonService;
    private LoginService loginService;
    private StudentLessonService studentLessonService;

    @Autowired
    public HomeController(LessonService lessonService, LoginService loginService, StudentLessonService studentLessonService) {
        this.lessonService = lessonService;
        this.loginService = loginService;
        this.studentLessonService = studentLessonService;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView goToHome(HttpServletRequest request) {
        ModelAndView model;
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        Long role = (long) request.getSession().getAttribute("ROLE");
        ModelMap data = new ModelMap();
        if (role == 2) {
            List<AlumnoClase> studentLessons = studentLessonService.getStudentLessonsCalificated(userId);
            data.addAttribute("lessons", studentLessons);
            model = new ModelAndView("studentHome", data);
        } else {

            Double average = 3.8;
            List<Calificacion> califications = null;

            data.addAttribute("average", average);
            data.addAttribute("califications", califications);

            model = new ModelAndView("professorHome");
        }

        return model;
    }

}
