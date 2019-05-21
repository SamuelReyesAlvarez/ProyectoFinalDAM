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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    @Id
    @Column(name = "id_estado")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEstado;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    @NotNull
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_atributo")
    @NotNull
    private Atributo atributo;

    @Column(name = "potenciado")
    @NotNull
    private int potenciado;

    public Estado() {
    }

    public Estado(int idEstado, Jugador jugador, Atributo atributo, int potenciado) {
        this.idEstado = idEstado;
        this.jugador = jugador;
        this.atributo = atributo;
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

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public int getPotenciado() {
        return potenciado;
    }

    public void setPotenciado(int potenciado) {
        this.potenciado = potenciado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.jugador);
        hash = 53 * hash + Objects.hashCode(this.atributo);
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
        if (!Objects.equals(this.jugador, other.jugador)) {
            return false;
        }
        if (!Objects.equals(this.atributo, other.atributo)) {
            return false;
        }
        return true;
    }
}
