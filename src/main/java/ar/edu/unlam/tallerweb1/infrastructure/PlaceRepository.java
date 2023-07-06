package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Lugar;

import java.util.List;

public interface PlaceRepository {
    Lugar getPlaceById(Long placeId);

    List<Lugar> getAllThePlaces();
}
