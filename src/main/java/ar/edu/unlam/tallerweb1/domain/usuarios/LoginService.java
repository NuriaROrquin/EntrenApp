package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface LoginService {

    Usuario getUserByEmailAndPassword(String email, String password);
}
