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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel Reyes
 */
@Entity
@Table(name = "registro_combate")
public class Combate implements Serializable {

    @Id
    @Column(name = "id_combate")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCombate;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_contrario")
    private Jugador contrario;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fecha;

    @Column(name = "resultado")
    @NotNull
    private boolean resultado;

    public Combate() {
    }

    public Combate(int idCombate, Jugador jugador, Jugador contrario, Date fecha, boolean resultado) {
        this.idCombate = idCombate;
        this.jugador = jugador;
        this.contrario = contrario;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public void setIdCombate(int idCombate) {
        this.idCombate = idCombate;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getContrario() {
        return contrario;
    }

    public void setContrario(Jugador contrario) {
        this.contrario = contrario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.fecha);
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
        final Combate other = (Combate) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
}
