package ar.edu.unlam.tallerweb1.domain.usuarios;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_clase")
    private long idClass;

    @Column(name = "fecha")
    private Date date;

    @Column(name = "isAprobada")
    private int isApproved;

    @Column(name = "isEliminada")
    private int isEliminated;

    @Column(name="fecha_alta")
    private Date openDate;

    @Column(name = "fecha_baja")
    private Date closingDate;

    @Column(name = "id_detalle")
    private int idDetail;

    @Column(name = "id_lugar")
    private int idPlace;

    @Column(name = "id_dificultad")
    private int idDifficulty;

    @Column(name = "id_disciplina")
    private int idDiscipline;





}
