package ar.edu.unlam.tallerweb1.delivery;
import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerStudent {


    @RequestMapping("/formsStudent")
    public ModelAndView irARegister() {

        ModelMap modelo = new ModelMap();

        modelo.put("registerStudentLesson", new DatosRegisterLessonStudent());

        return new ModelAndView("formsRegisterLessonStudent", modelo);
    }

    @RequestMapping("/formsLowClass")
    public ModelAndView irAHome() {

            return new ModelAndView("formsLowLessonStudent");

    }




}
