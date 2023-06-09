package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	Usuario getUserByEmailAndPassword(String email, String password);
	Usuario getUserByEmail(String email);
    void create(String email, String password, String rol);
}
