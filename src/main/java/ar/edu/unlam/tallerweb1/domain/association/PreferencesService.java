package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;

public interface PreferencesService {

    void savePreferences(DataPreferencesRegistration dataPreferencesRegistration, Long idUser);


}
