package vicente.marti.microserviciorespuestas.service;

import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microserviciorespuestas.entity.Respuesta;

import java.util.List;

public interface RespuestaService {

    List<Respuesta> saveAllRespuestas(List<Respuesta> respuestas);

    List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId);

}
