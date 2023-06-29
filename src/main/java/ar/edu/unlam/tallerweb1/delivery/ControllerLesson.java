package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonState;
import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerLesson {

    private LessonService LessonService;

    @Autowired
    public ControllerLesson(LessonService LessonService) {
        this.LessonService = LessonService;
    }


    @RequestMapping("/forms")
    public ModelAndView irARegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("registerLessonProfessor", new DatosRegisterLessonProfessor());

        return new ModelAndView("formsRegisterLessonProfessor", modelo);
    }

    @RequestMapping(value = "/registerLesson", method = RequestMethod.POST)
    public ModelAndView registerLesson(@Validated DatosRegisterLessonProfessor datosRegisterLessonProfessor, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idProfessor = (Long) request.getSession().getAttribute("ID_USER");
        LessonService.registerLesson(datosRegisterLessonProfessor, idProfessor);

        model.put("classPublicated", "La clase se ha registrado exitosamente");

        return new ModelAndView("registerLesson", model);
    }

    @RequestMapping("/lessons")
    public ModelAndView getLessons(HttpServletRequest request) {

        Object userId = request.getSession().getAttribute("ID_USER");

        List<Clase> classes;
        ModelMap model = new ModelMap();

        if ((long) request.getSession().getAttribute("ROL") == 2) {
            classes = LessonService.getLessonsByStudentId((Long) userId);

            model.addAttribute("classes", classes);

            return new ModelAndView("studentLessons", model);
        } else {
            classes = LessonService.getLessonsByProfessorId((Long) userId);

            model.addAttribute("classes", classes);

            return new ModelAndView("professorLessons", model);
        }


    }

    @RequestMapping(value = "/lessonsByState", method = RequestMethod.POST)
    public ModelAndView getLessonsByStateIdAndProfessorId(HttpServletRequest request, @Validated DataLessonState dataLessonState) {
        Long professorId = (Long) request.getSession().getAttribute("ID_USER");
        ModelMap model = new ModelMap();
        List<Clase> lessons = new ArrayList<>();
        lessons = LessonService.getLessonsDependingStateFromProfessor(professorId, dataLessonState.getIdState());
        model.addAttribute("classes", lessons);


        return new ModelAndView("professorLessons", model);
    }


}
