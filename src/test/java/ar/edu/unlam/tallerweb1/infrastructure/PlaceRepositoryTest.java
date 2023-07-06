package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback

    public void whenIRequestForAllPlacesShouldShowMeThem(){

        BasicData data = new BasicData();

        Lugar place = data.createPlace(1L,4858,4854,"Cancha de Patos - Libertad", "Cancha de Patos");

        session().save(place);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Lugar> criteriaQuery = criteriaBuilder.createQuery(Lugar.class);
        Root<Lugar> placeRoot = criteriaQuery.from(Lugar.class);


        List<Lugar> places = session().createQuery(criteriaQuery).getResultList();

        assertThat(places).isNotNull();
        assertThat(places).isNotEmpty();
        assertThat(places).hasSize(1);
    }

}
