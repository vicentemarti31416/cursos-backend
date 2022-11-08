package vicente.marti.microserviciorespuestas.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciorespuestas.entity.Respuesta;

import java.util.List;

@Repository
public interface RespuestaRepository extends MongoRepository<Respuesta, String> {


    @Query("{'alumnoId': ?0, 'preguntaId': {$in: ?1}}")
    List<Respuesta> findByAlumnoIdAndPreguntasId(Long alumnoId, List<Long> preguntasId);

    @Query("{'alumnoId': ?0}")
    List<Respuesta> findByAlumnoId(Long alumnoId);

    @Query(value = "{'alumnoId': ?0}", fields = "{'pregunta.examen.id': 1}")
    List<Respuesta> findIdExamenConRespuestaByAlumnoId(Long alumnoId);

    @Query("{'alumnoId': ?0, 'pregunta.examen.id': ?1}")
    List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    /*
    @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    List<Respuesta> findByAlumnoIdAndExamenId(Long alumnoId, Long examenId);

    @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
    List<Long> findIdExamenConRespuestaByAlumnoId(Long alumnoId);
    */
}
