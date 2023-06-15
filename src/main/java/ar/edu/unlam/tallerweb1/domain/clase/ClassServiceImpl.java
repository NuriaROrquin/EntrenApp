package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.ClassRepository;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("servicioClase")
@Transactional
public class ClassServiceImpl implements ClassService{

    private ClassRepository servicioClaseDao;
    private RepositorioUsuario servicioUsuarioDao;
    @Autowired
    public ClassServiceImpl(ClassRepository servicioClaseDao, RepositorioUsuario servicioUsuarioDao) {

        this.servicioClaseDao = servicioClaseDao;
        this.servicioUsuarioDao = servicioUsuarioDao;
    }

    @Override
    public List<AlumnoClase> getClassesByIdAlumno(Usuario alumno) {
        return servicioClaseDao.getClassesByIdAlumno(alumno);
    }

    @Override
    public List<Clase> getLessonsByProfessorId(Long id) {
        Usuario professor = servicioUsuarioDao.getUserById(id);
        List<Clase> lessons = servicioClaseDao.getClassesByProfessorId(professor);
        return lessons;
    }


}
