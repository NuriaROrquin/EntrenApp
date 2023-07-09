package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.delivery.models.DataPreferencesRegistration;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioPreferencias")
@Transactional

public class PreferencesServiceImpl implements PreferencesService {

    private PreferencesRepository servicePreferencesDao;
    private DisciplineRepository serviceDisciplineDao;
    private UserRepository serviceUserDao;

    @Autowired
    public PreferencesServiceImpl(PreferencesRepository servicePreferencesDao, DisciplineRepository serviceDisciplineDao, UserRepository serviceUserDao) {

        this.servicePreferencesDao = servicePreferencesDao;
        this.serviceDisciplineDao = serviceDisciplineDao;
        this.serviceUserDao = serviceUserDao;
    }

    @Override
    public void savePreferences(DataPreferencesRegistration dataPreferencesRegistration, Long idUser) {

        Usuario user = serviceUserDao.getUserById(idUser);

        List<Long> idDisciplinesList = dataPreferencesRegistration.getIdDiscipline();

        List<Disciplina> selectedPreferences = new ArrayList<>();


        if (idDisciplinesList != null) {

            for (Long idDiscipline : idDisciplinesList) {
                Disciplina discipline = serviceDisciplineDao.get(idDiscipline);
                selectedPreferences.add(discipline);
            }
        }

        List<Disciplina> disciplines = serviceDisciplineDao.getAllTheDisciplines();

        if (selectedPreferences != null) {
            for (int i = 0; i < disciplines.size(); i++) {
                disciplines.get(i).setPreferred(false);
                for (int j = 0; j < selectedPreferences.size(); j++) {
                    if (disciplines.get(i).getIdDiscipline() == selectedPreferences.get(j).getIdDiscipline()) {
                        disciplines.get(i).setPreferred(true);
                    }
                }
            }
        }

        for (int i = 0; i < disciplines.size(); i++) {
            Long idDiscipline = disciplines.get(i).getIdDiscipline();
            Preferencias preferenceToDelete = servicePreferencesDao.getByDisciplineIdAndUserId(idDiscipline, idUser);
            if (preferenceToDelete != null) {
                servicePreferencesDao.delete(preferenceToDelete);
            }
        }

        for (int i = 0; i < disciplines.size(); i++) {
            if (disciplines.get(i).getPreferred() == true) {
                servicePreferencesDao.create(user, disciplines.get(i));
            }
        }
    }
}
