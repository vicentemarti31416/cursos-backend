package vicente.marti.microserviciorespuestas.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vicente.marti.microserviciocommons.entity.Examen;

import java.util.List;

@FeignClient(name = "microservicio-examenes")
public interface ExamenesFeignClient {

    @GetMapping("/{id}")
    Examen findExamenById(@PathVariable Long id);

    @GetMapping("/examenes-respondidos")
    List<Long> getExamenesRespondidos(@RequestParam List<Long> preguntasId);
}
