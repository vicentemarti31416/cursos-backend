package vicente.marti.microserviciocursos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vicente.marti.microserviciocommons.entity.Alumno;

import java.util.List;

@FeignClient(name = "microservicio-usuarios")
public interface AlumnoFeignClient {

    @GetMapping("/alumnos-por-curso")
    List<Alumno> findAllAlumnosById(@RequestParam List<Long> alumnosId);
}
