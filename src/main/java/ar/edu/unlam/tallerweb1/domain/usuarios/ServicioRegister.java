package ar.edu.unlam.tallerweb1.domain.usuarios;

public interface ServicioRegister {
    Usuario consultarUsuario(String email);

    void registrarUsuario(String email, String password, String rol);
}
