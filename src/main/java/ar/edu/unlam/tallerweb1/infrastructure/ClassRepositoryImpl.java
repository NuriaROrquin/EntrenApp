package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClassRepository")
public class ClassRepositoryImpl implements ClassRepository {
    @Override
    public List<Clase> getClasses(Integer idUser) {
        return null;
    }
}
