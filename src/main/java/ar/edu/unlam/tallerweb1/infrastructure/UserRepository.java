package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

public interface UserRepository {
	Usuario getUserByEmailAndPassword(String email, String password);
	Usuario getUserByEmail(String email);
	Usuario getUserById(Long userId);
	boolean create(String email, String password, Rol rol, Long age, String Name);
}
