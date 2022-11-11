package vicente.marti.microserviciocommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {

    @Serial
    private static final long serialVersionUID = -8192921970504290532L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = " no puede estar vacío")
    private String nombre;

    @NotNull(message = " no puede estar vacío")
    private String apellido;

    @NotNull(message = " no puede estar vacío")
    @Email(message = " debe ser una dirección de email válida")
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Lob
    @JsonIgnore
    private byte[] photo;

    public Integer getPhotoHashCode() {
        return (this.photo != null)? Arrays.hashCode(this.photo) : null;
    }

    @PrePersist
    private void prePersist() {
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(!(object instanceof Alumno alumno)) {
            return false;
        }
        return this.id != null && this.id.equals(alumno.getId());
    }
}
