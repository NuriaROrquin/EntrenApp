package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerLesson {

    private LessonService LessonService;

    @Autowired
    public ControllerLesson(LessonService lessonService) {
        this.LessonService = lessonService;
    }


    @RequestMapping("/forms")
    public ModelAndView goToRegister() {

        ModelMap model = new ModelMap();

        model.put("registerLessonProfessor", new DataLessonRegistration());

        return new ModelAndView("formsRegisterLessonProfessor", model);
    }

    @RequestMapping(value = "/registerLesson", method = RequestMethod.POST)
    public ModelAndView registerLesson(@Validated DataLessonRegistration dataLessonRegistration, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idProfessor = (Long) request.getSession().getAttribute("ID_USER");
        LessonService.registerLesson(dataLessonRegistration, idProfessor);

        model.put("classPublished", "La clase se ha registrado exitosamente");

        return new ModelAndView("registerLesson", model);
    }

    @RequestMapping("/lessons")
    public ModelAndView getLessons(HttpServletRequest request) {

        Object userId = request.getSession().getAttribute("ID_USER");

        List<Clase> classes;
        ModelMap model = new ModelMap();

        if ((long) request.getSession().getAttribute("ROLE") == 2) {
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
    public ModelAndView getLessonsByStateIdAndProfessorId(HttpServletRequest request, @Validated DataLesson dataLesson) {
        Long professorId = (Long) request.getSession().getAttribute("ID_USER");
        ModelMap model = new ModelMap();
        List<Clase> lessons = LessonService.getLessonsDependingStateFromProfessor(professorId, dataLesson.getIdState());
        model.addAttribute("classes", lessons);
        return new ModelAndView("professorLessons", model);
    }

    @RequestMapping(value = "/cancelLesson", method = RequestMethod.POST)
    public ModelAndView cancelLesson(HttpServletRequest request, @Validated DataLesson dataLesson) {
        Long userId = (Long) request.getSession().getAttribute("ID_USER");
        ModelMap model = new ModelMap();
        List<Clase> lessons = LessonService.cancelLesson(dataLesson.getLessonId(), userId);
        model.addAttribute("cancelLessons", "La clase fue cancelada");
        model.addAttribute("classes", lessons);
        return new ModelAndView("professorLessons", model);
    }


}
