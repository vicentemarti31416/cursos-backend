package vicente.marti.microservicioexamenes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.entity.Asignatura;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microservicioexamenes.repository.AsignaturaRepository;
import vicente.marti.microservicioexamenes.repository.ExamenRepository;

import java.util.List;

@Service
@Transactional
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;


    @Transactional(readOnly = true)
    public List<Examen> findExamenByName(String str) {
        return repository.findExamenByName(str);
    }

    @Override
    public List<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> findIdExamenConRespuestaByPreguntasId(List<Long> preguntasId) {
        return repository.findIdExamenConRespuestaByPreguntasId(preguntasId);
    }
}
