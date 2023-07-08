package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferences;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;

import java.util.List;

public interface PreferencesService {

    void savePreferences(DataPreferences dataPreferences, Long idUser);


}
