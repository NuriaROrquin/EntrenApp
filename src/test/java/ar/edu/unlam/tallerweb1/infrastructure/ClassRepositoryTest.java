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
import org.hibernate.criterion.*;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassRepositoryTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void whenINeedAClassListShouldShowMeClassListReferToUser(){
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
        alumno.setRol("2");
        alumno.setName("Pablo");

        session().save(alumno);


        //profesor
        Usuario profesor = new Usuario();
        profesor.setId(3L);
        profesor.setRol("3");
        profesor.setName("Santi");

        session().save(profesor);


        //disciplina
        Disciplina disciplina = new Disciplina();
        disciplina.setName("Crossfit");

        session().save(disciplina);


        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(new Time(8, 0, 0));
        detail.setEndHour(new Time(9,0,0));

        session().save(detail);


        //clase
        Clase clase = new Clase();
        clase.setIdClass(1);
        clase.setDiscipline(disciplina);
        clase.setDate(new Date(2023, 06, 24));
        clase.setDetail(detail);

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


        Criteria criteria_alumno = session().createCriteria(UsuarioClase.class, "uca");
        criteria_alumno.setFetchMode("uca.user", FetchMode.JOIN);
        criteria_alumno.setFetchMode("uca.lesson", FetchMode.JOIN);
        criteria_alumno.createAlias("uca.user", "usuarioAlumno", JoinType.INNER.ordinal());
        criteria_alumno.add(Restrictions.eq("usuarioAlumno.rol", alumno.getRol()));

        Criteria criteria_profesor = session().createCriteria(UsuarioClase.class, "ucp");
        criteria_profesor.setFetchMode("ucp.user", FetchMode.JOIN);
        criteria_profesor.setFetchMode("ucp.lesson", FetchMode.JOIN);
        criteria_profesor.createAlias("ucp.user", "usuarioProfesor", JoinType.INNER.ordinal());
        criteria_profesor.add(Restrictions.eq("usuarioProfesor.rol", profesor.getRol()));

        

        List clases_alumno = criteria_alumno.list();
        //List clases_profesor = criteria_profesor.list();

        assertThat(clases_alumno).isNotEmpty();
        assertThat(clases_alumno).isNotNull();

        /*assertThat(clases_profesor).isNotEmpty();
        assertThat(clases_profesor).isNotNull();*/
    }

}