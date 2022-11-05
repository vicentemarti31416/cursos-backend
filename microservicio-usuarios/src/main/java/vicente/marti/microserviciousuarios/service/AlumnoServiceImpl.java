package vicente.marti.microserviciousuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vicente.marti.microserviciocommons.service.CommonServiceImpl;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciousuarios.feign.CursoFeignClient;
import vicente.marti.microserviciousuarios.repository.AlumnoRepository;

import java.util.List;

@Service
@Transactional
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Autowired
    private CursoFeignClient cursoFeignClient;

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
        this.deleteCursoAlumnoByAlumnoId(id);
    }

    @Override
    public void deleteCursoAlumnoByAlumnoId(Long alumnoId) {
        cursoFeignClient.deleteCursoAlumnoByAlumnoId(alumnoId);
    }

    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String str) {
        return repository.findByNombreOrApellido(str);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAllById(List<Long> alumnosId) {
        return repository.findAllById(alumnosId);
    }

}
