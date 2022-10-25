package vicente.marti.microserviciousuarios.service;

import vicente.marti.microserviciousuarios.entity.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<Alumno> findAlumnos();
    Optional<Alumno> findAlumnoById(Long id);
    Alumno addAlumno(Alumno alumno);
    void deleteAlumnoById(Long id);
}
