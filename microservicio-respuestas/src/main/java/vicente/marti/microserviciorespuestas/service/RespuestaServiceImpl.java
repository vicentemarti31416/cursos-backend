package vicente.marti.microserviciorespuestas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciorespuestas.entity.Respuesta;
import vicente.marti.microserviciorespuestas.repository.RespuestaRepository;

import java.util.List;

@Service
@Transactional
public class RespuestaServiceImpl extends CommonServiceImpl<Respuesta, RespuestaRepository> implements RespuestaService{

    @Transactional(readOnly = true)
    @Override
    public List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId) {
        return repository.findByAlumnoIdAndExamenId(alumnoId, examenId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId) {
        return repository.findIdExamenConRespuestaByAlumnoId(alumnoId);
    }
}
