package ar.edu.unlam.tallerweb1.domain.usuarios;

import javax.persistence.*;

@Entity
public class UsuarioClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_clase")
    private long idUserClass;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Clase lesson;


}
