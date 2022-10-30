package vicente.marti.microservicioexamenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciocommons.entity.Examen;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

    @Query("select e from Examen e where e.name like %?1%")
    List<Examen> findExamenByName(String str);
}
