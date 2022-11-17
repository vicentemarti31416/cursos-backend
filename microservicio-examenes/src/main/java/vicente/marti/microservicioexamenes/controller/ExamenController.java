package vicente.marti.microservicioexamenes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microserviciocommons.entity.Pregunta;
import vicente.marti.microservicioexamenes.service.ExamenService;

import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @GetMapping("/examenes-respondidos")
    public ResponseEntity<?> getExamenesRespondidos(@RequestParam List<Long> preguntasId) {
        return ResponseEntity.ok(service.findIdExamenConRespuestaByPreguntasId(preguntasId));
    }

    //Elimina del List de la base de datos cualquier pregunta que no esté en el nuevo List y lo persiste
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Examen> optional = service.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Examen examenDb = optional.get();
        examenDb.setName(examen.getName());
        List<Pregunta> preguntasEliminadas = examenDb.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .toList();
        preguntasEliminadas.forEach(examenDb::remove);
        examenDb.setPreguntas(examen.getPreguntas());
        examenDb.setAsignaturaPadre(examen.getAsignaturaPadre());
        examenDb.setAsignaturaHija(examen.getAsignaturaHija());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/buscar-examen/{str}")
    public ResponseEntity<?> buscarExamen(@PathVariable String str) {
        return ResponseEntity.ok(service.findExamenByName(str));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> findAllAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignaturas());
    }

}
