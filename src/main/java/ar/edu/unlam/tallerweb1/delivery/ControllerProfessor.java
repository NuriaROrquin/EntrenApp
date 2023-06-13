package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerProfessor {

    @RequestMapping("/forms")
    public ModelAndView irARegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("registerLessonProfessor", new DatosRegisterLessonProfessor());

        return new ModelAndView("formsRegisterLessonProfessor", modelo);
    }


}
