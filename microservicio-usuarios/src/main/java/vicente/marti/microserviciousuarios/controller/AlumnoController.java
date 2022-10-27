package vicente.marti.microserviciousuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciousuarios.entity.Alumno;
import vicente.marti.microserviciousuarios.service.AlumnoService;

import java.util.Optional;

@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(alumnoService.findAlumnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAlumnoById(@PathVariable Long id) {
        Optional<Alumno> optional = alumnoService.findAlumnoById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> addAlumno(@Validated @RequestBody Alumno alumno, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.addAlumno(alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@Validated @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
        Optional<Alumno> optional = alumnoService.findAlumnoById(id);
        Alumno alumnoDb = null;
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            alumnoDb = optional.get();
        }
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.addAlumno(alumnoDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumnoById(@PathVariable Long id) {
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.noContent().build();
    }

}
