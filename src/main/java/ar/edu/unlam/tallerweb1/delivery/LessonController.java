package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @RequestMapping("/register-lesson")
    public ModelAndView goToRegisterLesson(HttpServletRequest request) {

        if ((long) request.getSession().getAttribute("ROLE") == 3) {
            ModelMap model = new ModelMap();
            model.put("registerLesson", new DataLessonRegistration());
            List<Dificultad> difficulties = lessonService.getAllDifficulties();
            model.addAttribute("dificulties", difficulties);
            List<Disciplina> disciplines = lessonService.getAllDisciplines();
            model.addAttribute("disciplines", disciplines);
            List<Lugar> places = lessonService.getAllPlaces();
            model.addAttribute("places", places);
            return new ModelAndView("formsRegisterLesson", model);
        } else {
            ModelAndView model;
            model = new ModelAndView("noaccess");
            return model;
        }
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

    @RequestMapping(value = "/lessonsByState", method = RequestMethod.GET)
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
    public ModelAndView modifyLessonInformation(DataLesson dataLesson, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();

        List<Clase> lessons = lessonService.modifyLesson(dataLesson, userId);
        model.addAttribute("lessons", lessons);
        model.addAttribute("success","La clase fue modificada con exito!");

        return new ModelAndView("professorLessons",model);
    }

    @RequestMapping(value = "/getDataLesson", method = RequestMethod.GET)
    public ModelAndView getLessonById(HttpServletRequest request, long lessonId) {
        // Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();

        DataLessonRegistration lesson = lessonService.getLessonById(lessonId);
        List<Dificultad> difficulties = lessonService.getAllDifficulties();
        List<Disciplina> disciplines = lessonService.getAllDisciplines();
        List<Lugar> places = lessonService.getAllPlaces();
        model.addAttribute("dificulties", difficulties);
        model.addAttribute("places", places);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("lesson", lesson);

        return new ModelAndView("modifyLesson",model);
    }

    @RequestMapping(value = "/calificateLesson",method = RequestMethod.POST)
    public ModelAndView calificateLessonByStudent(HttpServletRequest request, DataCalification dataCalification){
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        List<Clase> studentLessons = lessonService.calificateLessonByStudent(dataCalification,userId);
        model.addAttribute("lessons", studentLessons);
        model.addAttribute("success", "La clase ha sido calificada con exito!");
        return new ModelAndView("studentLessons",model);
    }

    @RequestMapping(value = "/calificate", method = RequestMethod.GET)
    public ModelAndView getCalificationForm(HttpServletRequest request, long lessonId) {
        ModelMap model = new ModelMap();
        model.addAttribute("idLesson", lessonId);
        DataCalification dataCalification = new DataCalification();
        model.addAttribute("dataCalification", dataCalification);
        return new ModelAndView("calificationForm", model);
    }

    @RequestMapping(value = "availableLessons")
    public ModelAndView getAllAvailableLessons(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        List<Clase> availableLessons = lessonService.getAllAvailableLessons(userId);
        model.addAttribute("lessons", availableLessons);
        return new ModelAndView("availableLessons",model);
    }

    @RequestMapping(value = "/suggestedlessons")
    public ModelAndView getSuggestedLessons(HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        ModelMap model = new ModelMap();
        List<Clase> suggestedLessonsByPreferences = lessonService.getLessonsByPreferences(userId);
        List<Clase> suggestedLessonsByTaken = lessonService.getLessonsByTaken(userId);
        model.addAttribute("lessons", suggestedLessonsByPreferences);
        model.addAttribute("taken", suggestedLessonsByTaken);
        return new ModelAndView("suggestedLessons", model);
    }

    @RequestMapping(value = "/assingLesson",method = RequestMethod.POST)
    public ModelAndView setSelectedLesson(HttpServletRequest request, @Validated DataLesson dataLesson)
    {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        Long idLesson = (Long) dataLesson.getLessonId();
        lessonService.assingLesson(idLesson, userId);
        List<Clase> availableLessons = lessonService.getAllAvailableLessons(userId);
        ModelMap model = new ModelMap();
        model.addAttribute("lessons", availableLessons);
        model.put("success", "Se ha inscripto a la clase");
        //TODO ir a buscar el nombre de la clase para insertarlo en el modelo
        return new ModelAndView("availableLessons",model);
    }

    @RequestMapping(value = "/changeStateLessonForm",method = RequestMethod.GET)
    public ModelAndView changeStateLessonForm(HttpServletRequest request, DataLesson dataLesson){

        DataLesson data = new DataLesson();
        ModelMap model = new ModelMap();
        model.put("stateLesson", data);
        model.addAttribute("idLesson", dataLesson.getLessonId());
        return new ModelAndView("changeStateForm", model);
    }

    @RequestMapping(value = "/updateState",method = RequestMethod.POST)
    public RedirectView updateStateLesson(HttpServletRequest request, DataLesson dataLesson){
        lessonService.changeLessonState(dataLesson);
        DataLesson data = new DataLesson();
        ModelMap model = new ModelMap();
        model.put("stateLesson", data);
        String redirectUrl = "/lessonsByState?idState=0";
        return new RedirectView(redirectUrl);
    }

    @RequestMapping(value = "/lessondetail",method = RequestMethod.GET)
    public ModelAndView showLessonDetail(HttpServletRequest request){

        Long idLesson = Long.parseLong(request.getParameter("lessonId"));
        ModelMap model = new ModelMap();
        Clase lesson = lessonService.showLessonDetail(idLesson);
        model.addAttribute("lesson", lesson);

        return new ModelAndView("lessondetail", model);
    }

    @RequestMapping(value = "/unsubscribeLesson",method = RequestMethod.POST)
    public ModelAndView unsubscribeLesson(HttpServletRequest request, @Validated DataLesson dataLesson)
    {
        Long userId = (Long) request.getSession().getAttribute("USER_ID");
        Long idLesson = (Long) dataLesson.getLessonId();
        lessonService.unsubscribeLesson(idLesson, userId);
        List<Clase> lessons = lessonService.getLessonsByStudentId(userId);
        ModelMap model = new ModelMap();
        model.addAttribute("lessons", lessons);
        model.put("success", "Se ha cancelado la clase");
        return new ModelAndView("studentLessons",model);
    }
}