package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataRegister;
import ar.edu.unlam.tallerweb1.domain.user.RegisterService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    public ModelAndView goToRegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("register", new DataRegister());

        return new ModelAndView("register", modelo);
    }

    @RequestMapping("/register-validate")
    public ModelAndView validate(@ModelAttribute("register") DataRegister dataRegister) {
        ModelMap model = new ModelMap();

        Usuario user = registerService.getUserByEmail(dataRegister.getEmail());
        Boolean passwordMatch = dataRegister.getPassword().equals(dataRegister.getVerificatedPassword());

        if (passwordMatch) {
            return verifyUserInDatabase(dataRegister, model, user);
        }else {
            model.put("error", "Las contrasenas no coinciden");
            return new ModelAndView("register", model);
        }
    }

    private ModelAndView verifyUserInDatabase(DataRegister dataRegister, ModelMap model, Usuario user) {
        if(user == null){
            registerService.registerUser(dataRegister.getEmail(), dataRegister.getPassword(), dataRegister.getRole(), dataRegister.getAge(), dataRegister.getName());
            return new ModelAndView("redirect:/login");
        } else {
            model.put("error", "El mail ingresado ya existe en nuestro sistema");
            return new ModelAndView("register", model);
        }
    }

}
