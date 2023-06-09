package ar.edu.unlam.tallerweb1.domain.association.entities;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import javax.persistence.*;
@Entity
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario_rol")
    private long idUserRol;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Rol role;

    public long getIdUserRol() {
        return idUserRol;
    }

    public void setIdUserRol(long idUserRol) {
        this.idUserRol = idUserRol;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }
}
