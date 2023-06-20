package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class ControllerClass {

    private ClassService classService;

    @Autowired
    public ControllerClass(ClassService classService) {
        this.classService = classService;
    }
    @RequestMapping("/lessons")
    public ModelAndView getLessonsByProfessorId(HttpServletRequest request) {
        Object idProfessor = request.getSession().getAttribute("ID_USER");
        ModelMap model = new ModelMap();
        model.put("classes", classService.getLessonsByProfessorId((Long) idProfessor));

        return new ModelAndView("professorLessons", model);
    }
}
