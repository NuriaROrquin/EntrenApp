package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerLogin {

    private ServicioLogin loginService;

    @Autowired
    public ControllerLogin(ServicioLogin loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public ModelAndView goToLogin() {
        ModelMap modelo = new ModelMap();
        modelo.put("dataLogin", new DataLogin());
        return new ModelAndView("login", modelo);
    }

    @RequestMapping(path = "/login-validate", method = RequestMethod.POST)
    public ModelAndView validate(@ModelAttribute("dataLogin") DataLogin dataLogin, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario userResult = loginService.getUserByEmailAndPassword(dataLogin.getEmail(), dataLogin.getPassword());
        if (userResult != null) {
            request.getSession().setAttribute("ROLE", userResult.getRol().getIdRole());
            request.getSession().setAttribute("USER_ID", userResult.getId());
            return new ModelAndView("redirect:/home");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView init() {
        return new ModelAndView("redirect:/login");
    }
}
