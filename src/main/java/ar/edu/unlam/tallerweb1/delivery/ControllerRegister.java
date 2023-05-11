package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.IServicioRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerRegister {

    private IServicioRegister servicioRegister;
    @Autowired
    public ControllerRegister(IServicioRegister servicioRegister) {
        this.servicioRegister = servicioRegister;
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

        Usuario user = servicioRegister.consultarUsuario(datosRegister.getEmail());

        if(user == null){
            //servicioRegister.registrarUsuario(datosRegister.getEmail(), datosRegister.getPassword());
            return new ModelAndView("redirect:/home");
        } else {
            // si el usuario no existe agrega un mensaje de error en el modelo.
            model.put("error", "El mail ingresado ya existe en nuestro sistema");
            model.put("register", new DatosRegister());
        }
        return new ModelAndView("registroUsuario", model);
    }

}
