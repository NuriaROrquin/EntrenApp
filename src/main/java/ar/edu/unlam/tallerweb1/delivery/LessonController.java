package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @RequestMapping("/register-lesson")
    public ModelAndView goToRegisterLesson() {

        ModelMap model = new ModelMap();

        model.put("registerLesson", new DataLessonRegistration());

        return new ModelAndView("formsRegisterLesson", model);
    }

    @RequestMapping(value = "/validate-lesson", method = RequestMethod.POST)
    public ModelAndView validate(@ModelAttribute("register") DataLessonRegistration dataLessonRegistration, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long idProfessor = (Long) request.getSession().getAttribute("USER_ID");
        lessonService.registerLesson(dataLessonRegistration, idProfessor);

        model.put("classPublished", "La clase se ha registrado exitosamente");

        return new ModelAndView("registerLesson", model);
    }

    @RequestMapping("/lessons")
    public ModelAndView getLessons(HttpServletRequest request) {

        Object userId = request.getSession().getAttribute("USER_ID");

        List<Clase> lessons;
        ModelMap model = new ModelMap();

        if ((long) request.getSession().getAttribute("ROLE") == 2) {
            lessons = lessonService.getLessonsByStudentId((Long) userId);

            model.addAttribute("lessons", lessons);

            return new ModelAndView("studentLessons", model);
        } else {
            lessons = lessonService.getLessonsByProfessorId((Long) userId);

            model.addAttribute("lessons", lessons);

            return new ModelAndView("professorLessons", model);
        }
    }

    @RequestMapping(value = "/lessonsByState", method = RequestMethod.POST)
    public ModelAndView getLessonsByStateId(HttpServletRequest request, @Validated DataLesson dataLesson) {

        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        Long role = (Long) request.getSession().getAttribute("ROLE");

        ModelAndView view = new ModelAndView();

        List<Clase> lessons = lessonService.getLessonsByState(userId, dataLesson.getIdState());
        ModelMap model = new ModelMap();
        model.addAttribute("lessons", lessons);

        if (role == 3) {
            view.setViewName("professorLessons");
        }else{
            view.setViewName("studentLessons");
        }

        view.addAllObjects(model);

        return view;
    }

    @RequestMapping(value = "/cancelLesson", method = RequestMethod.POST)
    public ModelAndView cancelLesson(HttpServletRequest request, @Validated DataLesson dataLesson) {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        List<Clase> lessons = lessonService.cancelLesson(dataLesson.getLessonId(), userId);
        model.addAttribute("cancelLessons", "La clase fue cancelada");
        model.addAttribute("lessons", lessons);
        return new ModelAndView("professorLessons", model);
    }
    @RequestMapping(value = "/modifyLesson", method = RequestMethod.POST)
    public ModelAndView modifyLessonInformation(HttpServletRequest request, DataLesson dataLesson) {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        Dificultad difficulty = dataLesson.getDifficulty();
        Disciplina discipline = dataLesson.getDiscipline();
        Detalle detail = dataLesson.getDetail();
        Lugar place = dataLesson.getPlace();
        Date date = dataLesson.getDate();
        List<Clase> lessons = lessonService.modifyLesson(difficulty,detail,discipline,place,date, dataLesson.getLessonId(), userId);
        model.addAttribute(lessons);
        model.addAttribute("modifyLesson","La clase fue modificada con exito!");

        return new ModelAndView("modifyLesson",model);
    }



}
