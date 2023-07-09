package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository("classRepository")
public class LessonRepositoryImpl implements LessonRepository {

    private SessionFactory sessionFactory;


    @Autowired
    public LessonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Clase getLessonById(Long lessonId) {
        final Session session = sessionFactory.getCurrentSession();
        Clase lessonResult = (Clase) session.createCriteria(Clase.class)
                .add(Restrictions.eq("idClass", lessonId))
                .uniqueResult();
        return lessonResult;
    }

    @Override
    public List<Clase> getLessonsByStudent(Usuario student) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> userClaseRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase, Clase> claseJoin = userClaseRoot.join("lesson");
        Join<AlumnoClase, Usuario> alumnoJoin = userClaseRoot.join("user");

        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(alumnoJoin.get("id"), student.getId()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(userClaseRoot);

        List<AlumnoClase> lessons = session.createQuery(criteriaQuery).getResultList();

        List<Clase> lessonsList = lessons.stream()
                .map(AlumnoClase::getLesson)
                .collect(Collectors.toList());

        return lessonsList;
    }

    @Override
    public List<Clase> getLessonsByProfessor(Usuario professor) {
        final Session session = sessionFactory.getCurrentSession();


        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("id"), professor.getId()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);
        List<Clase> lessons = session.createQuery(criteriaQuery).getResultList();

        return lessons;
    }

    @Override
    public List<Clase> getLessonsByStateAndProfessor(Usuario professor, Estado state) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Join<Clase, Estado> estadoJoin = ClaseRoot.join("state");

        Predicate userPredicate = criteriaBuilder.equal(profesorJoin.get("id"), professor.getId());
        Predicate statePredicate;
        if (state == null) {
            statePredicate = criteriaBuilder.conjunction();
        } else {
            statePredicate = criteriaBuilder.equal(estadoJoin.get("idState"), state.getIdState());
        }
        Predicate predicate = criteriaBuilder.and(userPredicate, statePredicate);

        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session.createQuery(criteriaQuery).getResultList();
        return lessons;
    }

    @Override
    public void create(Dificultad difficulty, Detalle detail, Disciplina discipline, Lugar place, Date date, Usuario professor, Integer minimumAge, Integer maximumAge, String name, Estado state) {
        Clase lesson = new Clase();

        lesson.setDate(date);
        lesson.setOpenDate(Date.from(Instant.now()));
        lesson.setState(state);
        lesson.setDetail(detail);
        lesson.setPlace(place);
        lesson.setDifficulty(difficulty);
        lesson.setDiscipline(discipline);
        lesson.setProfesor(professor);
        lesson.setMinimum_age(minimumAge);
        lesson.setMaximum_age(maximumAge);
        lesson.setName(name);

        sessionFactory.getCurrentSession().save(lesson);
    }

    @Override
    public List<Clase> getLessonsByStateAndStudent(Usuario student, Estado state) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> studentRoot = criteriaQuery.from(AlumnoClase.class);

        Join<Clase, AlumnoClase> lessonJoin = studentRoot.join("lesson");
        Join<Usuario, AlumnoClase> userJoin = studentRoot.join("user");
        Join<Clase, Estado> stateJoin = lessonJoin.join("state");


        Predicate userPredicate = criteriaBuilder.equal(userJoin.get("id"), student.getId());
        Predicate statePredicate;
        if (state == null) {
            statePredicate = criteriaBuilder.conjunction();
        } else {
            statePredicate = criteriaBuilder.equal(stateJoin.get("idState"), state.getIdState());
        }

        Predicate predicate = criteriaBuilder.and(userPredicate, statePredicate);

        criteriaQuery.where(predicate);
        criteriaQuery.select(studentRoot);

        List<AlumnoClase> studentLessons = session.createQuery(criteriaQuery).getResultList();

        List<Clase> convertedLessons = studentLessons.stream().map(AlumnoClase::getLesson).collect(Collectors.toList());

        return convertedLessons;
    }

    @Override
    public void calificateLessonByStudent(Clase lesson, Calificacion calification, Usuario student) {
        final Session session = sessionFactory.getCurrentSession();
        Calificacion calificationResult = new Calificacion();
        calificationResult.setLesson(lesson);
        calificationResult.setUser(student);
        calificationResult.setDescription(calification.getDescription());
        calificationResult.setScore(calification.getScore());
        session.save(calificationResult);
    }

    @Override
    public void cancelLessonByProfessor(Clase lesson, Usuario professor) {
        final Session session = sessionFactory.getCurrentSession();
        Date actualDate = new Date();

        CriteriaBuilder criteriaBuilderLesson = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQueryLesson = criteriaBuilderLesson.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQueryLesson.from(Clase.class);
        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicateLesson = criteriaBuilderLesson.and(
                criteriaBuilderLesson.equal(profesorJoin.get("id"), professor.getId()),
                criteriaBuilderLesson.equal(ClaseRoot.get("idClass"), lesson.getIdClass())
        );
        criteriaQueryLesson.where(predicateLesson);
        criteriaQueryLesson.select(ClaseRoot);
        TypedQuery<Clase> typedQuery = session.createQuery(criteriaQueryLesson);
        typedQuery.setMaxResults(1);
        Clase lessonResult = typedQuery.getSingleResult();


        CriteriaBuilder criteriaBuilderState = session.getCriteriaBuilder();
        CriteriaQuery<Estado> criteriaQueryState = criteriaBuilderState.createQuery(Estado.class);
        Root<Estado> EstadoRoot = criteriaQueryState.from(Estado.class);
        Predicate predicateState = criteriaBuilderState.and(
                criteriaBuilderState.equal(EstadoRoot.get("description"), "CANCELADA")
        );
        criteriaQueryState.where(predicateState);
        criteriaQueryState.select(EstadoRoot);
        TypedQuery<Estado> typedQuery2 = session.createQuery(criteriaQueryState);
        typedQuery2.setMaxResults(1);
        Estado stateResult = typedQuery2.getSingleResult();

        lessonResult.setClosingDate(actualDate);
        lessonResult.setState(stateResult);

    }

    @Override
    public void modify(Dificultad difficulty, Disciplina discipline, Lugar place, Date date, Clase lesson, Usuario professor){
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilderLesson = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQueryLesson = criteriaBuilderLesson.createQuery(Clase.class);
        Root<Clase> LessonRoot = criteriaQueryLesson.from(Clase.class);
        Join<Clase, Usuario> professorJoin = LessonRoot.join("professor");
        Predicate predicateLesson = criteriaBuilderLesson.and(
                criteriaBuilderLesson.equal(professorJoin.get("id"), professor.getId()), criteriaBuilderLesson.equal(LessonRoot.get("idClass"), lesson.getIdClass()));
        criteriaQueryLesson.where(predicateLesson);
        criteriaQueryLesson.select(LessonRoot);
        TypedQuery<Clase> typedQueryLesson = session.createQuery(criteriaQueryLesson);
        typedQueryLesson.setMaxResults(1);
        Clase lessonResult = typedQueryLesson.getSingleResult();

        lessonResult.setDate(date);
        lessonResult.setDifficulty(difficulty);
        lessonResult.setDiscipline(discipline);
        lessonResult.setPlace(place);

        sessionFactory.getCurrentSession().update(lessonResult);
    }

    @Override
    public List<Clase> getAllAvailableLessons(Usuario student){

        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<AlumnoClase> alumnoClaseRoot = subquery.from(AlumnoClase.class);
        subquery.select(alumnoClaseRoot.get("lesson").get("idClass"))
                .where(criteriaBuilder.equal(alumnoClaseRoot.get("user"), student.getId()));

        criteriaQuery.select(claseRoot)
                .where(criteriaBuilder.not(claseRoot.get("idClass").in(subquery)),criteriaBuilder.equal(claseRoot.get("state").get("description"),"PENDIENTE"));
        List<Clase> availableLessons = session.createQuery(criteriaQuery).getResultList();

        return availableLessons;

    }

    @Override
    public List<Clase> getAllLessonsByPreferences(Usuario alumno){

        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        Subquery<Long> subqueryOne = criteriaQuery.subquery(Long.class);
        Root<AlumnoClase> alumnoClaseRoot = subqueryOne.from(AlumnoClase.class);
        subqueryOne.select(alumnoClaseRoot.get("lesson").get("idClass"))
                .where(criteriaBuilder.equal(alumnoClaseRoot.get("user"), alumno.getId()));

        Subquery<Long> subqueryTwo = criteriaQuery.subquery(Long.class);
        Root<Preferencias> preferencesRoot = subqueryTwo.from(Preferencias.class);
        subqueryTwo.select(preferencesRoot.get("discipline").get("idDiscipline"))
                .where(criteriaBuilder.equal(preferencesRoot.get("user"), alumno.getId()));

        criteriaQuery.select(claseRoot)
                .where(criteriaBuilder.not(claseRoot.get("idClass").in(subqueryOne)),
                        criteriaBuilder.equal(claseRoot.get("state").get("idState"), 1),
                        criteriaBuilder.in(claseRoot.get("discipline").get("idDiscipline")).value(subqueryTwo));

        List<Clase> lessonsByPreferences = session.createQuery(criteriaQuery).getResultList();
        return lessonsByPreferences;
    }
}
