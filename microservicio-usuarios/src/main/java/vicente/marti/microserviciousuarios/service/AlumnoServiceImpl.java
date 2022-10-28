package vicente.marti.microserviciousuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciousuarios.repository.AlumnoRepository;

import java.util.List;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String str) {
        return repository.findByNombreOrApellido(str);
    }
}
