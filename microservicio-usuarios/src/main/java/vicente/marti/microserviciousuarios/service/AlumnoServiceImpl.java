package vicente.marti.microserviciousuarios.service;

import org.springframework.stereotype.Service;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciousuarios.entity.Alumno;
import vicente.marti.microserviciousuarios.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

}
