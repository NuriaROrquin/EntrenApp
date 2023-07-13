package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private UserRepository serviceUserDao;

    @Autowired
    public ProfileServiceImpl(UserRepository serviceUserDao) {
        this.serviceUserDao = serviceUserDao;
    }


    @Override
    public Usuario getUserById(Long userId) {
        return serviceUserDao.getUserById(userId);
    }
}
