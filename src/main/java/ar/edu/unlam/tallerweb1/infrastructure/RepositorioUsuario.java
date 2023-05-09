package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;

import java.util.List;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);

    void guardar(Usuario usuario);

    Usuario buscar(String email);

    List<String> getMailUsuario();

    Usuario getUsuario(Long id);


    Usuario getUsuario(String mail);

    void modificarUsuario(Usuario usuario);

    List<Usuario> traerUsuarios();

    void delete(Usuario usuario);
}