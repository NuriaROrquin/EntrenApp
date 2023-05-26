package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {
    private ServicioLogin ServicioLogin;

    @Autowired
    public ControladorHome(ServicioLogin servicioLogin){
        this.ServicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/homeAlumno", method = RequestMethod.GET)
    public ModelAndView irAHomeAlumno() {
        return new ModelAndView("homeAlumno");
    }

    @RequestMapping(path = "/homeProfesor", method = RequestMethod.GET)
    public ModelAndView irAHomeProfesor() {
        return new ModelAndView("homeProfesor");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");
    }
}
