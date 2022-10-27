package vicente.marti.microserviciousuarios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciousuarios.entity.Alumno;
import vicente.marti.microserviciousuarios.service.AlumnoService;

import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Validated @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) return this.validate(result);
        Optional<Alumno> optional = service.findById(id);
        Alumno alumnoDb = null;
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            alumnoDb = optional.get();
        }
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

}
