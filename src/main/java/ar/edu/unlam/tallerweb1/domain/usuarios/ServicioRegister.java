package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

public interface ServicioRegister {
    Usuario getUserByEmail(String email);

    boolean registerUser(String email, String password, long rol);
}
