package vicente.marti.microserviciocursos.service;

import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.service.CommonService;
import vicente.marti.microserviciocursos.entity.Curso;

import java.util.List;

public interface CursoService extends CommonService<Curso> {

    Curso findByAlumnoId(Long id);
}
