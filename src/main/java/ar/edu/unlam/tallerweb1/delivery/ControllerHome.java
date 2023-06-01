package ar.edu.unlam.tallerweb1.delivery;

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

    @Autowired
    public ControllerHome(ServicioLogin servicioLogin){
        this.ServicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome(HttpServletRequest request) {

        if(request.getSession().getAttribute("ROL").equals("alumno")){
            return new ModelAndView("homeAlumno");
        }else{
            return new ModelAndView("homeProfesor");
        }


    }









}
