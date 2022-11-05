package vicente.marti.microserviciousuarios.service;

import vicente.marti.microserviciocommons.service.CommonService;
import vicente.marti.microserviciocommons.entity.Alumno;

import java.util.List;

public interface AlumnoService extends CommonService<Alumno> {

    List<Alumno> findByNombreOrApellido(String str);

    List<Alumno> findAllById(List<Long> alumnosId);

    void deleteCursoAlumnoByAlumnoId(Long alumnoId);
}
