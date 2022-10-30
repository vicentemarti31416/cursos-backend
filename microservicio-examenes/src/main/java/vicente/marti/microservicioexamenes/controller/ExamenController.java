package vicente.marti.microservicioexamenes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microservicioexamenes.service.ExamenService;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    //Elimina del List de la base de datos cualquier pregunta que no esté en el nuevo List y lo persiste
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Examen> optional = service.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Examen examenDb = optional.get();
        examenDb.setName(examen.getName());
        examenDb.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .forEach(examenDb::remove);
        examenDb.setPreguntas(examen.getPreguntas());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/buscar-examen/{str}")
    public ResponseEntity<?> buscarExamen(@PathVariable String str) {
        return ResponseEntity.ok(service.findExamenByName(str));
    }
}
