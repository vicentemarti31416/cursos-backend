package vicente.marti.microserviciocursos.entity;

import vicente.marti.microserviciocommons.entity.Alumno;

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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    public Curso() {
        this.alumnos = new ArrayList<>();
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void add(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void remove(Alumno alumno) {
        this.alumnos.remove(alumno);
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
}
