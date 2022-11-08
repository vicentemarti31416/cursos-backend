package vicente.marti.microserviciorespuestas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microserviciorespuestas.entity.Respuesta;
import vicente.marti.microserviciorespuestas.service.RespuestaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping("/respuestas")
    public ResponseEntity<?> saveAllRespuestas(@RequestBody List<Respuesta> respuestas, BindingResult result) {
        if (result.hasErrors()) return this.validate(result);
        respuestas = respuestas.stream().map(respuesta -> {
            respuesta.setAlumnoId(respuesta.getAlumno().getId());
            respuesta.setPreguntaId(respuesta.getPregunta().getId());
            return respuesta;
        }).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaService.saveAllRespuestas(respuestas));
    }

    @GetMapping("/alumnoId/{alumnoId}/examenId/{examenId}")
    public ResponseEntity<?> findByAlumnoIdAndExamenId(@PathVariable Long alumnoId, @PathVariable Long examenId) {
        List<Respuesta> respuestas = respuestaService.findByAlumnoIdAndExamenId(alumnoId, examenId);
        respuestas.forEach(respuesta -> {
            Examen examen = respuesta.getPregunta().getExamen();
            examen.setAnswered(true);
        });
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/examenes-respondidos/{alumnoId}")
    public ResponseEntity<?> findIdExamenConRespuestaByAlumnoId(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(respuestaService.findIdExamenConRespuestaByAlumnoId(alumnoId));
    }

    protected ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> errores.put(error.getField(), "El campo: ".concat(error.getField()).concat(Objects.requireNonNull(error.getDefaultMessage()))));
        return ResponseEntity.badRequest().body(errores);
    }
}
