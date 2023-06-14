package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.UsuarioClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClassRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeClassListReferToAlumno() {
        //rol Alumno
        Rol rolAlumno = new Rol();
        rolAlumno.setIdRole(2);

        session().save(rolAlumno);


        //rol Profesor
        Rol rolProfesor = new Rol();
        rolProfesor.setIdRole(3);

        session().save(rolProfesor);


        //alumno
        Usuario alumno = new Usuario();
        alumno.setId(2L);
        alumno.setRol(rolAlumno);
        alumno.setName("Pablo");

        session().save(alumno);


        //profesor
        Usuario profesor = new Usuario();
        profesor.setId(3L);
        profesor.setRol(rolProfesor);
        profesor.setName("Santi");

        session().save(profesor);


        //disciplina
        Disciplina disciplina = new Disciplina();
        disciplina.setName("Crossfit");

        session().save(disciplina);


        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(new Time(8, 0, 0));
        detail.setEndHour(new Time(9, 0, 0));

        session().save(detail);


        //clase
        Clase clase = new Clase();
        clase.setIdClass(1);
        clase.setDiscipline(disciplina);
        clase.setDate(new Date(2023, 06, 24));
        clase.setDetail(detail);
        clase.setProfesor(profesor);

        session().save(clase);


        //userclass
        UsuarioClase usuarioClaseAlumno = new UsuarioClase();
        usuarioClaseAlumno.setUser(alumno);
        usuarioClaseAlumno.setLesson(clase);
        session().save(usuarioClaseAlumno);

        UsuarioClase usuarioClaseProfesor = new UsuarioClase();
        usuarioClaseProfesor.setUser(profesor);
        usuarioClaseProfesor.setLesson(clase);
        session().save(usuarioClaseProfesor);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<UsuarioClase> criteriaQuery = criteriaBuilder.createQuery(UsuarioClase.class);
        Root<UsuarioClase> usuarioClaseRoot = criteriaQuery.from(UsuarioClase.class);
        Join<UsuarioClase, Clase> claseJoin = usuarioClaseRoot.join("lesson");
        Join<UsuarioClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        Join<Clase, Usuario> profesorJoin = claseJoin.join("profesor");

        criteriaQuery.select(usuarioClaseRoot);

        List<UsuarioClase> lessons = session().createQuery(criteriaQuery).getResultList();



        assertThat(lessons).isNotEmpty();
        assertThat(lessons).isNotNull();
        assertThat(lessons).extracting("lesson").contains(clase);
        assertThat(lessons).extracting("user").contains(alumno);
        assertThat(lessons).extracting("user").contains(profesor);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeAllTheClassesReferToProfessor(){

        //rol Alumno
        Rol rolAlumno = new Rol();
        rolAlumno.setIdRole(2);

        session().save(rolAlumno);

        //alumno
        Usuario alumno = new Usuario();
        alumno.setId(2L);
        alumno.setRol(rolAlumno);
        alumno.setName("Pablo");

        session().save(alumno);


        //rol Profesor
        Rol rolProfesor = new Rol();
        rolProfesor.setIdRole(3);

        session().save(rolProfesor);

        //profesor
        Usuario profesor = new Usuario();
        profesor.setId(3L);
        profesor.setRol(rolProfesor);
        profesor.setName("Santi");
        session().save(profesor);

        //disciplina
        Disciplina disciplina = new Disciplina();
        disciplina.setName("Crossfit");
        session().save(disciplina);


        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(new Time(8, 0, 0));
        detail.setEndHour(new Time(9, 0, 0));
        session().save(detail);


        //clase
        Clase clase = new Clase();
        clase.setIdClass(1);
        clase.setDiscipline(disciplina);
        clase.setDate(new Date(2023, 06, 24));
        clase.setDetail(detail);
        clase.setProfesor(profesor);
        session().save(clase);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");





        //Join<UsuarioClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        //Join<Clase, Usuario> profesorJoin = claseJoin.join("profesor");
    }

}