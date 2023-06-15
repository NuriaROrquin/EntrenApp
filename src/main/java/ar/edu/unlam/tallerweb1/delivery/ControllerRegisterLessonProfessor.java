package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.ServicioRegisterLessonProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerRegisterLessonProfessor {

    private ServicioRegisterLessonProfessor ServicioRegisterLessonProfessor;

    @Autowired
    public ControllerRegisterLessonProfessor(ServicioRegisterLessonProfessor servicioRegisterLessonProfessor) {
        this.ServicioRegisterLessonProfessor = servicioRegisterLessonProfessor;
    }

    @RequestMapping("/forms")
    public ModelAndView irARegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("registerLessonProfessor", new DatosRegisterLessonProfessor());

        return new ModelAndView("formsRegisterLessonProfessor", modelo);
    }

    @RequestMapping("/registerLesson")
    public ModelAndView registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor) {
        ModelMap model = new ModelMap();

        ServicioRegisterLessonProfessor.registerLesson(datosRegisterLessonProfessor);

        model.put("classPublicated", "La clase se ha registrado exitosamente");

        return new ModelAndView("registerLessonProfessor", model);

    }


}
