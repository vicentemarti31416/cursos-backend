package vicente.marti.microserviciorespuestas.service;

import vicente.marti.microserviciocommons.service.CommonService;
import vicente.marti.microserviciorespuestas.entity.Respuesta;

import java.util.List;

public interface RespuestaService extends CommonService<Respuesta> {

    List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId);
}
