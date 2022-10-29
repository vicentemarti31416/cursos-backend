package vicente.marti.microservicioexamenes.service;

import org.springframework.stereotype.Service;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microservicioexamenes.entity.Examen;
import vicente.marti.microservicioexamenes.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {
}
