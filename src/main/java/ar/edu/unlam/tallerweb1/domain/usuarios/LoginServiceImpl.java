package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioLogin")
@Transactional
public class LoginServiceImpl implements LoginService {

    private RepositorioUsuario servicioLoginDao;

    @Autowired
    public LoginServiceImpl(RepositorioUsuario servicioLoginDao) {
        this.servicioLoginDao = servicioLoginDao;
    }

    @Override
    public Usuario getUserByEmailAndPassword(String email, String password) {
        return servicioLoginDao.getUserByEmailAndPassword(email, password);
    }
}
