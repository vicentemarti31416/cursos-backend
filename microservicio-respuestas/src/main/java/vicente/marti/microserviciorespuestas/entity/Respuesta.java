package vicente.marti.microserviciorespuestas.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.entity.Pregunta;

import java.io.Serializable;

@Document(collection = "respuestas")
public class Respuesta implements Serializable {


    private static final long serialVersionUID = -9132867786098502772L;

    @Id
    private String id;

    private String text;

    @Transient
    private Alumno alumno;

    private Long alumnoId;

    @Transient
    private Pregunta pregunta;

    private Long preguntaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }
}
