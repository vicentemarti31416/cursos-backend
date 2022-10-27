package vicente.marti.microserviciocursos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocursos.entity.Curso;
import vicente.marti.microserviciocursos.service.CursoService;

import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        cursoDb.setName(curso.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }
}
