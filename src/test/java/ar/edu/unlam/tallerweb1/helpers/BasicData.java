package ar.edu.unlam.tallerweb1.helpers;

import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.awt.*;
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

    public Disciplina createDiscipline(long id, String name, TextArea description, int minimumAge, int maximumAge){

        Disciplina discipline = new Disciplina();
        discipline.setIdDiscipline(id);
        discipline.setName(name);
        discipline.setDescription(description);
        discipline.setMinimum_age(minimumAge);
        discipline.setMaximum_age(maximumAge);
        return discipline;
    }

    public Detalle createDetail(long id, Date startHour, Date endHour, int capacity ){
        Detalle detail = new Detalle();
        detail.setIdDetail(id);
        detail.setStartHour(startHour);
        detail.setEndHour(endHour);
        detail.setCapacity(capacity);
        return detail;
    }

    public Date setHourMinutes(int hours, int minutes){
        Calendar calendar = Calendar.getInstance(); // trae la hora actual.
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        Date finalDate = calendar.getTime();
        return finalDate;
    }

    public Lugar createPlace(long id, long latitude, long longitude, TextArea description, String name){
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
