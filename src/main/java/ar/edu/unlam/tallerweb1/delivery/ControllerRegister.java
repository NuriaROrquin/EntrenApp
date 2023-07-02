package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerRegister {

    private ServicioRegister ServicioRegister;

    @Autowired
    public ControllerRegister(ServicioRegister servicioRegister){
        this.ServicioRegister = servicioRegister;
    }

    @RequestMapping("/registrar-usuario")
    public ModelAndView goToRegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("register", new DataRegister());

        return new ModelAndView("register", modelo);
    }

    @RequestMapping("/registrarme")
    public ModelAndView registrarme(DataRegister datosRegister) {
        ModelMap model = new ModelMap();

        Usuario user = ServicioRegister.consultarUsuario(datosRegister.getEmail());
        Boolean coincideContrasena = datosRegister.getPassword().equals(datosRegister.getVerificatedPassword());

        if (coincideContrasena==true) {
            return verificateUserDatabase(datosRegister, model, user);
        }else {
            model.put("error", "Las contrasenas no coinciden");
            return new ModelAndView("register", model);
        }
    }

    private ModelAndView verificateUserDatabase(DataRegister datosRegister, ModelMap model, Usuario user) {
        if(user == null){
            ServicioRegister.registrarUsuario(datosRegister.getEmail(), datosRegister.getPassword(), datosRegister.getRole());
            return new ModelAndView("redirect:/login");
        } else {
            model.put("error", "El mail ingresado ya existe en nuestro sistema");
            return new ModelAndView("register", model);
        }
    }

}
