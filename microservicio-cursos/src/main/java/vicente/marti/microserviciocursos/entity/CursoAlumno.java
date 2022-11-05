package vicente.marti.microserviciocursos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vicente.marti.microserviciocommons.entity.Alumno;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cursos_alumnos")
public class CursoAlumno implements Serializable {

    @Serial
    private static final long serialVersionUID = 8719973084474087495L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alumno_id", unique = true)
    private Long alumnoId;

    @JsonIgnoreProperties({"cursoAlumnos"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(!(object instanceof CursoAlumno cursoAlumno)) {
            return false;
        }
        return this.alumnoId != null && this.alumnoId.equals(cursoAlumno.alumnoId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
