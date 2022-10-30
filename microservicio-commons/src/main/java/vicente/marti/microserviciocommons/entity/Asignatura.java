package vicente.marti.microserviciocommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable {

    @Serial
    private static final long serialVersionUID = -1794725914444671495L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties({"hijos"})
    @ManyToOne(fetch = FetchType.EAGER)
    private Asignatura padre;

    @JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL)
    private List<Asignatura> hijos;

    public Asignatura() {
        this.hijos = new ArrayList<>();
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

    public Asignatura getPadre() {
        return padre;
    }

    public void setPadre(Asignatura padre) {
        this.padre = padre;
    }

    public List<Asignatura> getHijos() {
        return hijos;
    }

    public void setHijos(List<Asignatura> hijos) {
        this.hijos = hijos;
    }
}
