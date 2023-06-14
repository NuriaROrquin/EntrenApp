package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioRegister")
@Transactional
public class ClassServiceImpl implements ClassService{

    private ClassRepository servicioClaseDao;

    @Autowired
    public void ClassServiceImpl(ClassRepository servicioClaseDao) {
        this.servicioClaseDao = servicioClaseDao;
    }

    @Override
    public List<AlumnoClase> getClassesByIdAlumno(Usuario alumno) {
        return servicioClaseDao.getClassesByIdAlumno(alumno);
    }

}
