package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;

public interface RoleRepository {

    Rol getRolById(long id);

}
