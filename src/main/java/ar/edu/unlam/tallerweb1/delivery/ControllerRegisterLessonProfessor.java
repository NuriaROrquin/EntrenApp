package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.ServicioRegisterLessonProfessor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    @RequestMapping(value = "/registerLesson", method = RequestMethod.POST)
    public ModelAndView registerLesson(@Validated DatosRegisterLessonProfessor datosRegisterLessonProfessor, BindingResult bindingResult, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        if(bindingResult.hasErrors()){
            model.put("error", "Algun dato es erroneo");
            return new ModelAndView("formsRegisterLessonProfessor", model);
        }else{
            Long idProfessor = (Long) request.getSession().getAttribute("ID_USER");
            ServicioRegisterLessonProfessor.registerLesson(datosRegisterLessonProfessor, idProfessor);

            model.put("classPublicated", "La clase se ha registrado exitosamente");

            return new ModelAndView("registerLesson", model);
        }

    }


}
