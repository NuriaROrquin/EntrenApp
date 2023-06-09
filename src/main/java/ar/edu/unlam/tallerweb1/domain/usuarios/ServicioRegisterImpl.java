package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegister")
@Transactional
public class ServicioRegisterImpl implements ServicioRegister {

    private RepositorioUsuario servicioRegisterDao;

    @Autowired
    public ServicioRegisterImpl(RepositorioUsuario servicioRegisterDao){
        this.servicioRegisterDao = servicioRegisterDao;
    }

    public Usuario consultarUsuario (String email) {
        Usuario user = servicioRegisterDao.getUserByEmail(email);
        return user;
    }

    @Override
    public void registrarUsuario(String email, String password, String rol) {
        servicioRegisterDao.create(email,password,rol);
    }
}
