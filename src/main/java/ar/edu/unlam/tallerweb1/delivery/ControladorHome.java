package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorHome {
    private ServicioLogin ServicioLogin;

    @Autowired
    public ControladorHome(ServicioLogin servicioLogin){
        this.ServicioLogin = servicioLogin;
    }

}
