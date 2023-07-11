package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

public interface RegisterService {
    Usuario getUserByEmail(String email);

    boolean registerUser(String email, String password, long rol, Long age, String name);
}
