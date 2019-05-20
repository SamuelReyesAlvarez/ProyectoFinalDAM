/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "equipo")
public class Equipo implements Serializable {

    public enum TipoEquipo {
        PENDIENTE, CASCO, COLLAR, PULSERA, CHALECO, CAPA, ESCUDO, PANTALON, ARMA, CINTURON, BOTAS, ANILLO
    }

    @Id
    @Column(name = "id_equipo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEquipo;

    @Column(name = "tipo_equipo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoEquipo tipoEquipo;

    @Column(name = "nivel")
    @Min(1)
    @NotNull
    private int nivel;

    @OneToMany
    @JoinColumn(name = "id_equipo")
    private HashSet<Inventario> equipoJugador;

    public Equipo() {
    }

    public Equipo(int idEquipo, TipoEquipo tipoEquipo, int nivel, HashSet<Inventario> equipoJugador) {
        this.idEquipo = idEquipo;
        this.tipoEquipo = tipoEquipo;
        this.nivel = nivel;
        this.equipoJugador = equipoJugador;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public HashSet<Inventario> getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(HashSet<Inventario> equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.tipoEquipo);
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
        final Equipo other = (Equipo) obj;
        if (this.tipoEquipo != other.tipoEquipo) {
            return false;
        }
        return true;
    }
}
