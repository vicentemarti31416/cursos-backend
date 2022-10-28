package vicente.marti.microserviciocursos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocursos.entity.Curso;
import vicente.marti.microserviciocursos.service.CursoService;

import java.util.List;
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

    @PutMapping("/{idCurso}/add-alumnos")
    public ResponseEntity<?> addAlumnos(@Validated @RequestBody List<Alumno> alumnos, BindingResult result, @PathVariable Long idCurso) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(idCurso);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        alumnos.forEach(cursoDb::add);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{idCurso}/remove-alumno")
    public ResponseEntity<?> removeAlumno(@RequestBody Alumno alumno, BindingResult result, @PathVariable Long idCurso) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(idCurso);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        cursoDb.remove(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @GetMapping("/curso-alumno/{idAlumno}")
    public ResponseEntity<?> cursoAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(service.findByAlumnoId(idAlumno));
    }

}
