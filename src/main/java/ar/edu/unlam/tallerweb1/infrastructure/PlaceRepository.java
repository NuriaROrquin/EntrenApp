package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Lugar;

public interface PlaceRepository {
    Lugar getPlaceById(Long placeId);
}
