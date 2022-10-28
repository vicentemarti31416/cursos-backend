package vicente.marti.microserviciocursos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocursos.entity.Curso;
import vicente.marti.microserviciocursos.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Transactional(readOnly = true)
    public Curso findByAlumnoId(Long id) {
        return repository.findByAlumnoId(id);
    }
}
