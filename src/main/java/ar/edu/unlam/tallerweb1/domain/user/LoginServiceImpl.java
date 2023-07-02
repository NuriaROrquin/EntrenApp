package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioLogin")
@Transactional
public class LoginServiceImpl implements LoginService {

    private UserRepository servicioLoginDao;

    @Autowired
    public LoginServiceImpl(UserRepository servicioLoginDao) {
        this.servicioLoginDao = servicioLoginDao;
    }

    @Override
    public Usuario getUserByEmailAndPassword(String email, String password) {
        return servicioLoginDao.getUserByEmailAndPassword(email, password);
    }
}
