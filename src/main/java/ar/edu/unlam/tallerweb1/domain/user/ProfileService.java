package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;


public interface ProfileService {

    Usuario getUserById(Long userId);
}
