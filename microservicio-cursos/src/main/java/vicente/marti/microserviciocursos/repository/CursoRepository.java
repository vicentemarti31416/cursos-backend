package vicente.marti.microserviciocursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vicente.marti.microserviciocursos.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select c from Curso c join fetch c.cursoAlumnos a where a.alumnoId = ?1")
    Curso findByAlumnoId(Long id);

    @Modifying
    @Query("delete from CursoAlumno ca where ca.alumnoId = ?1")
    void deleteCursoAlumnoByAlumnoId(Long alumnoId);
}
