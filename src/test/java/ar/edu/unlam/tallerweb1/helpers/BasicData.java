package ar.edu.unlam.tallerweb1.helpers;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BasicData {

    public Clase createLesson(Date date, Date openDate, Date closingDate, Detalle detail, Lugar place, Dificultad difficulty, Disciplina discipline, Usuario professor, Estado state, String name, Integer minimumAge, Integer maximumAge) {
        Clase lesson = new Clase();
        lesson.setDate(date);
        lesson.setOpenDate(openDate);
        lesson.setClosingDate(closingDate);
        lesson.setDetail(detail);
        lesson.setPlace(place);
        lesson.setDifficulty(difficulty);
        lesson.setDiscipline(discipline);
        lesson.setProfesor(professor);
        lesson.setState(state);
        lesson.setName(name);
        lesson.setMinimum_age(minimumAge);
        lesson.setMaximum_age(maximumAge);
        return lesson;
    }

    public Disciplina createDiscipline(long id, String description) {

        Disciplina discipline = new Disciplina();
        discipline.setIdDiscipline(id);
        discipline.setDescription(description);
        return discipline;
    }

    public Detalle createDetail(long id, LocalTime startHour, LocalTime endHour, int capacity) {
        Detalle detail = new Detalle();
        detail.setIdDetail(id);
        detail.setStartHour(startHour);
        detail.setEndHour(endHour);
        detail.setCapacity(capacity);
        return detail;
    }

    public LocalTime setHourMinutes(int hours, int minutes) {
        String timeString = String.format("%02d:%02d", hours, minutes);
        LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        return localTime;
    }

    public LocalTime setHourMinutes(LocalTime hours) {
        LocalTime localTime = hours;
        return localTime;
    }

    public Lugar createPlace(Double latitude, Double longitude, String name) {
        Lugar place = new Lugar();
        place.setLatitude(latitude);
        place.setLongitude(longitude);
        place.setName(name);
        return place;

    }

    public Dificultad createDifficulty(long id, String description) {
        Dificultad difficulty = new Dificultad();
        difficulty.setIdDifficulty(id);
        difficulty.setDescription(description);
        return difficulty;

    }

    public Estado createState(long id, String description) {
        Estado state = new Estado();
        state.setIdState(id);
        state.setDescription(description);
        return state;
    }

    public Rol createRole(long id, String description) {
        Rol role = new Rol();
        role.setIdRole(id);
        role.setDescription(description);
        return role;
    }

    public AlumnoClase createAlumnoClase(long id, Usuario user, Clase lesson, Calificacion calification) {
        AlumnoClase studentLesson = new AlumnoClase();
        studentLesson.setIdUserClass(id);
        studentLesson.setUser(user);
        studentLesson.setLesson(lesson);
        studentLesson.setCalification(calification);
        return studentLesson;
    }

    public Usuario createUser(long id, String email, String password, String name, Rol role, Boolean activo, Long age) {
        Usuario user = new Usuario();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setRol(role);
        user.setActivo(activo);
        user.setAge(age);
        return user;
    }

    public Calificacion createCalification(Long id, String description, int score, Usuario user, Clase lesson){
        Calificacion calification = new Calificacion();
        calification.setIdCalification(id);
        calification.setDescription(description);
        calification.setScore(score);
        calification.setUser(user);
        calification.setLesson(lesson);
        return calification;
    }

    public Preferencias createPreferences(Long id, Usuario user, Disciplina discipline){
        Preferencias preference = new Preferencias();
        preference.setIdPreferences(id);
        preference.setUser(user);
        preference.setDiscipline(discipline);

        return preference;
    }


}
