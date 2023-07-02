package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;

public interface RoleRepository {

    Rol getRoleById(long roleId);

}
