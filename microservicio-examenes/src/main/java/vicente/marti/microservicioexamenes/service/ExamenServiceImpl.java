package vicente.marti.microservicioexamenes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microservicioexamenes.repository.ExamenRepository;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Transactional(readOnly = true)
    public List<Examen> findExamenByName(String str) {
        return repository.findExamenByName(str);
    }
}
