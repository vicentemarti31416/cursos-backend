package vicente.marti.microservicioexamenes.service;

import vicente.marti.microserviciocommons.service.CommonService;
import vicente.marti.microserviciocommons.entity.Examen;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {

    List<Examen> findExamenByName(String str);
}
