package vicente.marti.microserviciorespuestas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciorespuestas.entity.Respuesta;
import vicente.marti.microserviciorespuestas.service.RespuestaService;

import java.util.List;

@RestController
public class RespuestaController extends CommonController<Respuesta, RespuestaService> {

    @PostMapping("/respuestas")
    public ResponseEntity<?> saveAll(@RequestBody List<Respuesta> respuestas, BindingResult result) {
        if (result.hasErrors()) return this.validate(result);
        respuestas = respuestas.stream().map(respuesta -> {
            respuesta.setAlumnoId(respuesta.getAlumno().getId());
            return respuesta;
        }).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(respuestas));
    }

    @GetMapping("/alumnoId/{alumnoId}/examenId/{examenId}")
    public ResponseEntity<?> findByAlumnoIdAndExamenId(@PathVariable Long alumnoId, @PathVariable Long examenId) {
        return ResponseEntity.ok(service.findByAlumnoIdAndExamenId(alumnoId, examenId));
    }

    @GetMapping("/examenes-respondidos/{alumnoId}")
    public ResponseEntity<?> findIdExamenConRespuestaByAlumnoId(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(service.findIdExamenConRespuestaByAlumnoId(alumnoId));
    }
}
