package vicente.marti.microserviciocursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciocursos.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
