package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("studentLessonService")
@Transactional
public class StudentLessonServiceImpl implements StudentLessonService {

    private UserRepository userServiceDao;
    private StudentLessonRepository studentLessonRepository;
    @Autowired
    public StudentLessonServiceImpl(UserRepository userServiceDao, StudentLessonRepository studentLessonRepository) {
        this.userServiceDao = userServiceDao;
        this.studentLessonRepository = studentLessonRepository;
    }

    @Override
    public List<AlumnoClase> getStudentLessonsCalificated(Long studentId) {
        Usuario student = userServiceDao.getUserById(studentId);
        List<AlumnoClase> studentLessons = studentLessonRepository.getStudentLessonsCalificated(student);
        return studentLessons;
    }
}
