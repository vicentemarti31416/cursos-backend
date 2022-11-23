package vicente.marti.microserviciousuarios.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vicente.marti.microserviciocursos.entity.Curso;

import java.util.List;

@FeignClient(name = "microservicio-cursos")
public interface CursoFeignClient {

    @DeleteMapping("/delete-cursos-alumnos/{alumnoId}")
    void deleteCursoAlumnoByAlumnoId(@PathVariable Long alumnoId);

    @GetMapping("/")
    List<Curso> findAll();
}
