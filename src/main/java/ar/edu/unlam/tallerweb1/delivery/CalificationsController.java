package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.domain.association.CalificationService;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CalificationsController {

    private LessonService lessonService;
    private LoginService loginService;
    private CalificationService calificationService;

    @Autowired
    public CalificationsController(LessonService lessonService, LoginService loginService,CalificationService calificationService ) {
        this.lessonService = lessonService;
        this.loginService = loginService;
        this.calificationService = calificationService;
    }

    @RequestMapping(value = "/showCalifications",method = RequestMethod.GET)
    public ModelAndView showAllCalifications(HttpServletRequest request, DataCalification dataCalification){
        Long professorId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        List<Calificacion> professorCalifications = calificationService.getProfessorCalifications(professorId, null);
        model.addAttribute("califications",professorCalifications);
        return new ModelAndView("califications",model);
    }
}
