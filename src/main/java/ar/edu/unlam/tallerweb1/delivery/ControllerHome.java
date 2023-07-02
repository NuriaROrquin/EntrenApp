package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.usuarios.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerHome {

    @Autowired
    public ControllerHome() {
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
