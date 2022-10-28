package vicente.marti.microserviciousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciocommons.entity.Alumno;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
    List<Alumno> findByNombreOrApellido(String str);
}
