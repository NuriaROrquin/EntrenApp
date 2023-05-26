package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuario(String email, String password);
	Usuario buscarMail(String email);
    Usuario buscar(String email);
	void modificar(Usuario usuario);

    void registrar(String email, String password, String rol);


}
