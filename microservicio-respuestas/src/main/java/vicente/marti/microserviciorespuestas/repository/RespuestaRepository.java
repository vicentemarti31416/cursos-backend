package vicente.marti.microserviciorespuestas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciorespuestas.entity.Respuesta;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
    List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId);
}
