package ar.edu.unlam.tallerweb1.helpers;

import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class BasicData {

    public Clase createClase(int id, Date date, Date openDate, Date closingDate, Detalle detail, Lugar place, Dificultad difficulty,  Disciplina discipline, Usuario professor) {

        Clase lesson = new Clase();
        lesson.setIdClass(id);
        lesson.setDate(date);
        lesson.setOpenDate(openDate);
        lesson.setClosingDate(closingDate);
        lesson.setDetail(detail);
        lesson.setPlace(place);
        lesson.setDifficulty(difficulty);
        lesson.setDiscipline(discipline);
        lesson.setProfesor(professor);
        return lesson;
    }

    public Disciplina createDiscipline(long id, String name, String description, int minimumAge, int maximumAge){

        Disciplina discipline = new Disciplina();
        discipline.setIdDiscipline(id);
        discipline.setName(name);
        discipline.setDescription(description);
        discipline.setMinimum_age(minimumAge);
        discipline.setMaximum_age(maximumAge);
        return discipline;
    }

    public Detalle createDetail(long id, LocalTime startHour, LocalTime endHour, int capacity ){
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

    public Lugar createPlace(long id, long latitude, long longitude, String description, String name){
        Lugar place = new Lugar();
        place.setIdPlace(id);
        place.setLatitude(latitude);
        place.setLongitude(longitude);
        place.setDescription(description);
        place.setName(name);
        return place;

    }

    public Dificultad createDifficulty (long id, String description){
        Dificultad difficulty = new Dificultad();
        difficulty.setIdDifficulty(id);
        difficulty.setDescription(description);
        return difficulty;

    }



}
