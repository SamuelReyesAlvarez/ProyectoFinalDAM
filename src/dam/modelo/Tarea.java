/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

    @Id
    @Column(name = "id_tarea")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTarea;

    @Column(name = "descripcion")
    @Size(min = 15, max = 250)
    @NotNull
    private String descripcion;

    @OneToMany(mappedBy = "tarea")
    @Cascade(CascadeType.ALL)
    private List<Mision> tareasDisponibles;

    public Tarea() {
    }

    public Tarea(int idTarea, String descripcion, List<Mision> tareasDisponibles) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.tareasDisponibles = tareasDisponibles;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Mision> getTareasDisponibles() {
        return tareasDisponibles;
    }

    public void setTareasDisponibles(List<Mision> tareasDisponibles) {
        this.tareasDisponibles = tareasDisponibles;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarea other = (Tarea) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
}
