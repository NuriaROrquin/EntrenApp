package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PreferencesRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenIAskForTheSuggestedLessonsItShouldAppear(){

        BasicData data = new BasicData();

        Rol role = data.createRole(1L, "alumno");
        session().save(role);

        Usuario alumno = data.createUser(1L, "alumno@unlam.edu.ar", "1234", "Alumno", role, true);
        session().save(alumno);

        Disciplina disciplineOne = data.createDiscipline(1L, "Fútbol");
        Disciplina disciplineTwo = data.createDiscipline(1L, "Básquet");
        Disciplina disciplineThree = data.createDiscipline(1L, "Rugby");
        Disciplina disciplineFour = data.createDiscipline(1L, "Yoga");
        session().save(disciplineOne);
        session().save(disciplineTwo);
        session().save(disciplineThree);
        session().save(disciplineFour);

        Preferencias preferenceOne = data.createPreferences(1L, alumno, disciplineOne);
        Preferencias preferenceTwo = data.createPreferences(1L, alumno, disciplineTwo);
        Preferencias preferenceThree = data.createPreferences(1L, alumno, disciplineThree);
        session().save(preferenceOne);
        session().save(preferenceTwo);
        session().save(preferenceThree);

        List<Disciplina> expectedDisciplineList = new ArrayList<>();
        expectedDisciplineList.add(disciplineOne);
        expectedDisciplineList.add(disciplineTwo);
        expectedDisciplineList.add(disciplineThree);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Disciplina> criteriaQuery = criteriaBuilder.createQuery(Disciplina.class);
        Root<Preferencias> studentRoot = criteriaQuery.from(Preferencias.class);

        Join<Preferencias, Disciplina> disciplineJoin = studentRoot.join("discipline");

        Predicate userPredicate = criteriaBuilder.equal(studentRoot.get("user").get("id"), alumno.getId());

        Predicate predicate = criteriaBuilder.and(userPredicate);

        criteriaQuery.where(predicate);
        criteriaQuery.select(disciplineJoin);

        List<Disciplina> disciplineList = session().createQuery(criteriaQuery).getResultList();

        assertThat(disciplineList).isNotNull();
        assertThat(disciplineList).isNotEmpty();
        assertThat(disciplineList).hasSize(3);
        assertThat(disciplineList).isEqualTo(expectedDisciplineList);
    }
}
