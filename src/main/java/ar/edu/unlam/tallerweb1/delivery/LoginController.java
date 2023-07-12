package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLogin;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
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

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView init() {
        //TODO verificar si tiene usuario en sesion. Si tiene, redireccionar a la home. Si no, dejar pasar al login.
        return new ModelAndView("redirect:/login");
    }
}
