package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DifficultityRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback

    public void whenIRequestForAllDifficultiesShouldShowMeThem(){

        BasicData data = new BasicData();

        Dificultad difficulties = data.createDifficulty(1L,"Intermedia");

        session().save(difficulties);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Dificultad> criteriaQuery = criteriaBuilder.createQuery(Dificultad.class);
        Root<Dificultad> placeRoot = criteriaQuery.from(Dificultad.class);


        List<Dificultad> places = session().createQuery(criteriaQuery).getResultList();

        assertThat(places).isNotNull();
        assertThat(places).isNotEmpty();
        assertThat(places).hasSize(1);
    }

}
