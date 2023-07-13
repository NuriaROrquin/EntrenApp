package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.CalificationRepository;
import ar.edu.unlam.tallerweb1.infrastructure.StudentLessonRepository;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalificationServiceImpl implements CalificationService{

    private CalificationRepository calificationServiceDao;
    private UserRepository userServiceDao;


    @Autowired
    public CalificationServiceImpl(CalificationRepository calificationServiceDao, UserRepository userServiceDao) {
        this.calificationServiceDao= calificationServiceDao;
        this.userServiceDao = userServiceDao;

    }

    @Override
    public List<Calificacion> getProfessorCalifications(Long professorId, Integer limit) {
        Usuario professor = userServiceDao.getUserById(professorId);
        List<Calificacion> professorCalifications = calificationServiceDao.getProfessorCalificationsDao(professor,limit);
        return professorCalifications;
    }

    @Override
    public Double getProfessorCalificationsAverage(Long professorId) {
        Usuario professor = userServiceDao.getUserById(professorId);
        Double professorAverage = calificationServiceDao.getProfessorAverage(professor);
        return professorAverage;
    }
}
