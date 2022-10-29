package vicente.marti.microserviciocursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciocursos.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select c from Curso c join fetch c.alumnos a where a.id = ?1")
    Curso findByAlumnoId(Long id);
}