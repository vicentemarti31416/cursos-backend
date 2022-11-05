package vicente.marti.microserviciorespuestas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.entity.Pregunta;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "respuestas")
public class Respuesta implements Serializable {

    @Serial
    private static final long serialVersionUID = -9132867786098502772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Transient
    private Alumno alumno;

    @Column(name = "alumno_id")
    private Long alumnoId;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
}
