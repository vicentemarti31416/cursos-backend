package vicente.marti.microservicioexamenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vicente.marti.microservicioexamenes.entity.Examen;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
}
