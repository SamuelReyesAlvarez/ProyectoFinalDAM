/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    public enum TipoAtributo {
        FUERZA, DESTREZA, ARMADURA, CONSTITUCION, TIERRA, AGUA, FUEGO, VIENTO
    }

    @Id
    @Column(name = "id_estado")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEstado;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @Column(name = "tipo_atributo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoAtributo tipoAtributo;

    @Column(name = "potenciado")
    @NotNull
    private int potenciado;

    public Estado() {
    }

    public Estado(int idEstado, Jugador jugador, TipoAtributo tipoAtributo, int potenciado) {
        this.idEstado = idEstado;
        this.jugador = jugador;
        this.tipoAtributo = tipoAtributo;
        this.potenciado = potenciado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(TipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public int getPotenciado() {
        return potenciado;
    }

    public void setPotenciado(int potenciado) {
        this.potenciado = potenciado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.tipoAtributo);
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
        final Estado other = (Estado) obj;
        if (this.tipoAtributo != other.tipoAtributo) {
            return false;
        }
        return true;
    }
}
