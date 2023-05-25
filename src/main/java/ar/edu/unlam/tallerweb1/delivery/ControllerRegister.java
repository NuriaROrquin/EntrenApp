package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.IServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.IServicioRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControllerRegister {

    private IServicioRegister IServicioRegister;

    @Autowired
    public ControllerRegister(IServicioRegister servicioRegister){
        this.IServicioRegister = servicioRegister;
    }

    @RequestMapping("/registrar-usuario")
    public ModelAndView irARegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("register", new DatosRegister());

        return new ModelAndView("registroUsuario", modelo);
    }

    @RequestMapping("/registrarme")
    public ModelAndView registrarme(DatosRegister datosRegister) {
        ModelMap model = new ModelMap();
        System.out.println(datosRegister.getRol());
        Usuario user = IServicioRegister.consultarUsuario(datosRegister.getEmail());
        Boolean coincideContrasena = datosRegister.getPassword().equals(datosRegister.getVerificatedPassword());

        if (coincideContrasena==true) {
            if(user == null){
                IServicioRegister.registrarUsuario(datosRegister.getEmail(), datosRegister.getPassword(), datosRegister.getRol());
                return new ModelAndView("redirect:/login");
            } else {
                model.put("error", "El mail ingresado ya existe en nuestro sistema");
                model.put("register", new DatosRegister());
            }
        }else {
            model.put("error", "Las contraseñas no coinciden");
            model.put("register", new DatosRegister());
        }
        return new ModelAndView("registroUsuario", model);
    }

}
