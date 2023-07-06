package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DisciplineRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback

    public void whenIRequestForAllDifficultiesShouldShowMeThem(){

        BasicData data = new BasicData();

        Disciplina discipline = data.createDiscipline(1L,"Fútbol","Fúbol 5 - Infantil", 7,10);

        session().save(discipline);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Disciplina> criteriaQuery = criteriaBuilder.createQuery(Disciplina.class);
        Root<Disciplina> placeRoot = criteriaQuery.from(Disciplina.class);


        List<Disciplina> places = session().createQuery(criteriaQuery).getResultList();

        assertThat(places).isNotNull();
        assertThat(places).isNotEmpty();
        assertThat(places).hasSize(1);
    }


}
