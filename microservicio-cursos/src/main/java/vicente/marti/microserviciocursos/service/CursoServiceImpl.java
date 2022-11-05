package vicente.marti.microserviciocursos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocursos.entity.Curso;
import vicente.marti.microserviciocursos.feign.AlumnoFeignClient;
import vicente.marti.microserviciocursos.feign.RespuestaFeignClient;
import vicente.marti.microserviciocursos.repository.CursoRepository;

import java.util.List;

@Service
@Transactional
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    private RespuestaFeignClient respuestaFeignClient;

    @Autowired
    private AlumnoFeignClient alumnoFeignClient;

    @Override
    public List<Alumno> findAllAlumnosById(List<Long> alumnosId) {
        return alumnoFeignClient.findAllAlumnosById(alumnosId);
    }

    @Override
    public void deleteCursoAlumnoByAlumnoId(Long alumnoId) {
        repository.deleteCursoAlumnoByAlumnoId(alumnoId);
    }

    @Transactional(readOnly = true)
    @Override
    public Curso findByAlumnoId(Long id) {
        return repository.findByAlumnoId(id);
    }

    @Override
    public List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId) {
        return respuestaFeignClient.findIdExamenConRespuestaByAlumnoId(alumnoId);
    }

}
