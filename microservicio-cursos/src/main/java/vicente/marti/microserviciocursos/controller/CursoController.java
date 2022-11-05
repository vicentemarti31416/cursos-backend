package vicente.marti.microserviciocursos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.entity.Examen;
import vicente.marti.microserviciocursos.entity.Curso;
import vicente.marti.microserviciocursos.entity.CursoAlumno;
import vicente.marti.microserviciocursos.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @GetMapping("/")
    @Override
    public ResponseEntity<?> findAll() {
        List<Curso> cursos = service.findAll().stream().map(curso -> {
            curso.getCursoAlumnos().forEach(cursoAlumno -> {
                Alumno alumno = new Alumno();
                alumno.setId(cursoAlumno.getId());
                curso.add(alumno);
            }); return curso;
        }).toList();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/pageable")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Curso> cursos = service.findAll(pageable).map(curso -> {
            curso.getCursoAlumnos().forEach(cursoAlumno -> {
                Alumno alumno = new Alumno();
                alumno.setId(cursoAlumno.getId());
                curso.add(alumno);
            }); return curso;
        });
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Curso> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = optional.get();
        List<Long> alumnosId = curso.getCursoAlumnos().stream().map(CursoAlumno::getAlumnoId).toList();
        List<Alumno> alumnos = service.findAllAlumnosById(alumnosId);
        curso.setAlumnos(alumnos);
        return ResponseEntity.ok(curso);
    }

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
        alumnos.forEach(alumno -> {
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(alumno.getId());
            cursoAlumno.setCurso(cursoDb);
            cursoDb.addCursoAlumno(cursoAlumno);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{idCurso}/remove-alumno")
    public ResponseEntity<?> removeAlumno(@RequestBody Alumno alumno, BindingResult result, @PathVariable Long idCurso) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(idCurso);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setAlumnoId(alumno.getId());
        cursoDb.removeCursoAlumno(cursoAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{idCurso}/add-examenes")
    public ResponseEntity<?> addExamenes(@Validated @RequestBody List<Examen> examenes, BindingResult result, @PathVariable Long idCurso) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(idCurso);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        examenes.forEach(cursoDb::add);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @PutMapping("/{idCurso}/remove-examen")
    public ResponseEntity<?> removeExamen(@RequestBody Examen examen, BindingResult result, @PathVariable Long idCurso) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Curso> optional = service.findById(idCurso);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Curso cursoDb = optional.get();
        cursoDb.remove(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDb));
    }

    @GetMapping("/curso-alumno/{idAlumno}")
    public ResponseEntity<?> cursoAlumno(@PathVariable Long idAlumno) {
        Curso curso = service.findByAlumnoId(idAlumno);
        if (curso != null) {
            List<Long> examenesId = service.findIdExamenConRespuestaByAlumnoId(idAlumno);
            List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
                if (examenesId.contains(examen.getId())) {
                    examen.setAnswered(true);
                }
                return examen;
            }).toList();
            curso.setExamenes(examenes);
        }
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/delete-cursos-alumnos/{alumnoId}")
    public ResponseEntity<?> deleteCursoAlumnoByAlumnoId(@PathVariable Long alumnoId) {
        service.deleteCursoAlumnoByAlumnoId(alumnoId);
        return ResponseEntity.noContent().build();
    }

}
