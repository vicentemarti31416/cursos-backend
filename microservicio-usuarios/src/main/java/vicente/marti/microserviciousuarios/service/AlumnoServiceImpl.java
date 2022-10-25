package vicente.marti.microserviciousuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciousuarios.entity.Alumno;
import vicente.marti.microserviciousuarios.repository.AlumnoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findAlumnoById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public Alumno addAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public void deleteAlumnoById(Long id) {
        alumnoRepository.deleteById(id);
    }
}
