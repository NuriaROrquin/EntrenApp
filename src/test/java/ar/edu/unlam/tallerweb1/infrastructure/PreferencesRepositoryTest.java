package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PreferencesRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenICompleteTheLessonFormShouldCreateALesson(){
        BasicData data = new BasicData();
        Rol role = data.createRole(2L, "Alumno");
        Usuario alumno = data.createUser(1L, "alumno@unlam.com", "1234", "Santiago", role, true);
        Disciplina futbol = data.createDiscipline(1L,"Fútbol 5", "Fútbol en cancha de 5", 10, 15);
        Disciplina basquet = data.createDiscipline(2L, "Básquet", "El deporte de negros", 20, 25);
        Disciplina criquet = data.createDiscipline(3L, "Criquet", "Quién conoce este deporte y cómo puede ser el segundo más popular del mundo", 3, 7);
        Disciplina rugby = data.createDiscipline(4L, "Rugby", "Guarda que te tackleo hasta en el boliche", 1, 65);

        Preferencias preferenceOne = data.createPreferences(1L, alumno, futbol);
        Preferencias preferenceTwo = data.createPreferences(1L, alumno, basquet);
        Preferencias preferenceThree = data.createPreferences(1L, alumno, criquet);
        Preferencias preferenceFour = data.createPreferences(1L, alumno, rugby);

        session().save(role);
        session().save(alumno);
        session().save(futbol);
        session().save(basquet);
        session().save(criquet);
        session().save(rugby);
        session().save(preferenceOne);
        session().save(preferenceTwo);
        session().save(preferenceThree);
        session().save(preferenceFour);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Preferencias> criteriaQuery = criteriaBuilder.createQuery(Preferencias.class);
        Root<Preferencias> claseRoot = criteriaQuery.from(Preferencias.class);

        List<Preferencias> preferences = session().createQuery(criteriaQuery).getResultList();

        assertThat(preferences).isNotNull();
        assertThat(preferences).isNotEmpty();
        assertThat(preferences).hasSize(4);
        assertThat(preferences).extracting("user").contains(alumno);
        assertThat(preferences).extracting("discipline").contains(futbol);
        assertThat(preferences).extracting("discipline").contains(basquet);
        assertThat(preferences).extracting("discipline").contains(criquet);
        assertThat(preferences).extracting("discipline").contains(rugby);
    }

}
