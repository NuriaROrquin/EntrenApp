package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RoleRepository;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegister")
@Transactional
public class RegisterServiceImpl implements RegisterService {

    private UserRepository servicioRegisterDao;
    private RoleRepository servicioRolDao;

    @Autowired
    public RegisterServiceImpl(UserRepository servicioRegisterDao, RoleRepository servicioRolDao){
        this.servicioRegisterDao = servicioRegisterDao;
        this.servicioRolDao = servicioRolDao;
    }

    public Usuario getUserByEmail(String email) {
        Usuario user = servicioRegisterDao.getUserByEmail(email);
        return user;
    }

    @Override
    public boolean registerUser(String email, String password, long idRol) {
        Rol rol = servicioRolDao.getRolById(idRol);
        boolean isCreated = servicioRegisterDao.create(email,password,rol);
        return isCreated;
    }
}
