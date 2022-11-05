package vicente.marti.microserviciocursos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservicio-respuestas")
public interface RespuestaFeignClient {

    @GetMapping("/examenes-respondidos/{alumnoId}")
    List<Long> findIdExamenConRespuestaByAlumnoId(@PathVariable Long alumnoId);
}
