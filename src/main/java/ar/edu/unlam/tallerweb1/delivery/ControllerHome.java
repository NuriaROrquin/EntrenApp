package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerHome {
    private ServicioLogin ServicioLogin;
    private LessonService ServicioClase;

    @Autowired
    public ControllerHome(ServicioLogin servicioLogin, LessonService servicioClase) {
        this.ServicioLogin = servicioLogin;
        this.ServicioClase = servicioClase;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {

        ModelAndView model;

        if ((long)request.getSession().getAttribute("ROL") == 2) {
            model = new ModelAndView("homeAlumno");
            //model.addObject("classList", "clases");
        } else {
            model = new ModelAndView("homeProfesor");
        }

        return model;
    }



}
