package vicente.marti.microserviciousuarios.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vicente.marti.microserviciocommons.controller.CommonController;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciousuarios.service.AlumnoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @GetMapping("/alumnos-por-curso")
    public ResponseEntity<?> findAllById(@RequestParam List<Long> alumnosId) {
        return ResponseEntity.ok(service.findAllById(alumnosId));
    }

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

    @GetMapping("/buscar/{str}")
    public ResponseEntity<?> findByNombreOrApellido(@PathVariable String str) {
        return  ResponseEntity.ok(service.findByNombreOrApellido(str));
    }

    @PostMapping("/save-with-photo")
    public ResponseEntity<?> saveWithPhoto(@Validated Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
        if (!archivo.isEmpty()) alumno.setPhoto(archivo.getBytes());
        return super.save(alumno, result);
    }

    @PutMapping("/update-with-photo/{id}")
    public ResponseEntity<?> updateWithPhoto(@Validated Alumno alumno, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {
        if (result.hasErrors()) return this.validate(result);
        Optional<Alumno> optional = service.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Alumno alumnoDb = optional.get();
        alumnoDb.setNombre(alumno.getNombre());
        alumnoDb.setApellido(alumno.getApellido());
        alumnoDb.setEmail(alumno.getEmail());
        if (!archivo.isEmpty()) alumnoDb.setPhoto(archivo.getBytes());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id) {
        Optional<Alumno> optional = service.findById(id);
        if (optional.isEmpty() || optional.get().getPhoto() == null) return ResponseEntity.notFound().build();
        Resource resource = new ByteArrayResource(optional.get().getPhoto());
        return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).body(resource);
    }


    @DeleteMapping("/delete-cursos-alumnos/{alumnoId}")
    public ResponseEntity<?> deleteCursoAlumnoByAlumnoId(@PathVariable Long alumnoId) {
        service.deleteCursoAlumnoByAlumnoId(alumnoId);
        return ResponseEntity.noContent().build();
    }

}
