package vicente.marti.microserviciocursos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vicente.marti.microserviciocommons.entity.Alumno;
import vicente.marti.microserviciocommons.entity.Examen;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {
    @Serial
    private static final long serialVersionUID = -4225764717739444650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    //@OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Alumno> alumnos;

    @JsonIgnoreProperties(value = {"curso"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoAlumno> cursoAlumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
        this.cursoAlumnos = new ArrayList<>();
    }

    public List<CursoAlumno> getCursoAlumnos() {
        return cursoAlumnos;
    }

    public void setCursoAlumnos(List<CursoAlumno> cursoAlumnos) {
        this.cursoAlumnos = cursoAlumnos;
    }

    public void addCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.add(cursoAlumno);
    }

    public void removeCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumnos.remove(cursoAlumno);
    }

    public void add(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void remove(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public void add(Examen examen) {
        this.examenes.add(examen);
    }

    public void remove(Examen examen) {
        this.examenes.remove(examen);
    }

    @PrePersist
    public void prePersist() {
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
}
