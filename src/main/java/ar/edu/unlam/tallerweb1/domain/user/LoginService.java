package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface LoginService {

    Usuario getUserByEmailAndPassword(String email, String password);
}
