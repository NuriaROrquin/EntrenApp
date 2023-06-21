package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

public interface ServicioRegister {
    Usuario consultarUsuario(String email);

    void registrarUsuario(String email, String password, long rol);
}
