package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

public interface RepositorioUsuario {
	Usuario getUserByEmailAndPassword(String email, String password);
	Usuario getUserByEmail(String email);
    void create(String email, String password, long rol);

	Usuario getUserById(Long id);
    void create(String email, String password, Rol rol);
}
