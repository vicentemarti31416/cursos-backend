package vicente.marti.microserviciousuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciousuarios.entity.Alumno;
import vicente.marti.microserviciousuarios.repository.AlumnoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

}
