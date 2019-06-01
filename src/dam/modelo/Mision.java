/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "mision")
public class Mision implements Serializable {

    private static final int RECOMPENSA_POR_HORA = 100;
    private static final int DURACION_MAXIMA = 8;
    private static final int DURACION_MINIMA = 1;

    @Id
    @Column(name = "id_mision")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMision;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @Column(name = "descripcion")
    @Size(min = 15, max = 250)
    @NotNull
    private String descripcion;

    @Column(name = "duracion")
    @Min(DURACION_MINIMA)
    @Max(DURACION_MAXIMA)
    private int duracion;

    @Column(name = "inicio")
    @Temporal(TemporalType.DATE)
    private Date inicio;

    @Column(name = "fin")
    @Temporal(TemporalType.DATE)
    private Date fin;

    @Column(name = "recompensa")
    @NotNull
    private int recompensa;

    @Column(name = "completada")
    @Type(type = "boolean")
    @NotNull
    private boolean completada;

    public Mision() {
    }

    public Mision(int idMision, Jugador jugador, String descripcion, Date inicio, Date fin, int recompensa, boolean completada) {
        this.idMision = idMision;
        this.jugador = jugador;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.recompensa = recompensa;
        this.completada = completada;
    }

    public int getIdMision() {
        return idMision;
    }

    public void setIdMision(int idMision) {
        this.idMision = idMision;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.inicio);
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
        final Mision other = (Mision) obj;
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        return true;
    }
}
