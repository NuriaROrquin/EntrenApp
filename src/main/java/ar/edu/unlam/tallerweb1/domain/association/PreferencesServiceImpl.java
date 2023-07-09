package ar.edu.unlam.tallerweb1.domain.association;

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

public class PreferencesServiceImpl implements PreferencesService{

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

        for (Long idDiscipline : idDisciplinesList){
            Disciplina discipline = serviceDisciplineDao.get(idDiscipline);
            selectedPreferences.add(discipline);
        }

        for (int i = 0; i < selectedPreferences.size(); i++){
            servicePreferencesDao.create(user, selectedPreferences.get(i));

        }




    }
}
