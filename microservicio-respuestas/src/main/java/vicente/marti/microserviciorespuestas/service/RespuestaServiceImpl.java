package vicente.marti.microserviciorespuestas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microserviciocommons.entity.Pregunta;
import vicente.marti.microserviciorespuestas.entity.Respuesta;
import vicente.marti.microserviciorespuestas.feign.ExamenesFeignClient;
import vicente.marti.microserviciorespuestas.repository.RespuestaRepository;

import java.util.Collections;
import java.util.List;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private ExamenesFeignClient examenesFeignClient;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Override
    public List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId) {
        /*E
        xamen examen = examenesFeignClient.findExamenById(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntasId = preguntas.stream().map(Pregunta::getId).toList();
        List<Respuesta> respuestas = respuestaRepository.findByAlumnoIdAndPreguntasId(alumnoId, preguntasId);
        respuestas = respuestas.stream().map(respuesta -> {
            preguntas.forEach(pregunta -> {
                if (pregunta.getId() == respuesta.getPreguntaId()) respuesta.setPregunta(pregunta);
            }); return respuesta;
        }).toList();
        */
        return respuestaRepository.findByAlumnoIdAndExamenId(alumnoId, examenId);
    }

    @Override
    public List<Respuesta> saveAllRespuestas(List<Respuesta> respuestas) {
        return respuestaRepository.saveAll(respuestas);
    }

    @Override
    public List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId) {
        /*
        List<Respuesta> respuestas = respuestaRepository.findByAlumnoId(alumnoId);
        List<Long> examenesId = Collections.emptyList();
        if (respuestas.size() > 0) {
            List<Long> preguntasId = respuestas.stream().map(Respuesta::getPreguntaId).toList();
            examenesId = examenesFeignClient.getExamenesRespondidos(preguntasId);
        }
        */
        List<Respuesta> respuestas = respuestaRepository.findIdExamenConRespuestaByAlumnoId(alumnoId);
        return respuestas.stream().map(respuesta -> respuesta.getPregunta().getExamen().getId()).distinct().toList();
    }
}
