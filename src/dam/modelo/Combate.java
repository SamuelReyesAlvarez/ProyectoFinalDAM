/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
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
 * @author Samuel Reyes Alvarez
 *
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

    @Column(name = "nivel_jugador")
    @NotNull
    private int nivelJugador;

    @Column(name = "nivel_contrario")
    @NotNull
    private int nivelContrario;

    @Column(name = "vida_jugador")
    @NotNull
    private int vidaJugador;

    @Column(name = "vida_contrario")
    @NotNull
    private int vidaContrario;

    @Column(name = "ataque_total_jugador")
    @NotNull
    private int ataqueTotalJugador;

    @Column(name = "ataque_total_contrario")
    @NotNull
    private int ataqueTotalContrario;

    @Column(name = "defensa_total_jugador")
    @NotNull
    private int defensaTotalJugador;

    @Column(name = "defensa_total_contrario")
    @NotNull
    private int defensaTotalContrario;

    @Column(name = "puntos_jugador")
    @NotNull
    private int puntosJugador;

    @Column(name = "puntos_contrario")
    @NotNull
    private int puntosContrario;

    public Combate() {
    }

    public Combate(Jugador jugador, Jugador contrario, int nivelJugador, int nivelContrario, int vidaJugador, int vidaContrario, int ataqueTotalJugador, int ataqueTotalContrario, int defensaTotalJugador, int defensaTotalContrario, int puntosJugador, int puntosContrario) {
        this.jugador = jugador;
        this.contrario = contrario;
        this.nivelJugador = nivelJugador;
        this.nivelContrario = nivelContrario;
        this.vidaJugador = vidaJugador;
        this.vidaContrario = vidaContrario;
        this.ataqueTotalJugador = ataqueTotalJugador;
        this.ataqueTotalContrario = ataqueTotalContrario;
        this.defensaTotalJugador = defensaTotalJugador;
        this.defensaTotalContrario = defensaTotalContrario;
        this.puntosJugador = puntosJugador;
        this.puntosContrario = puntosContrario;
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

    public int getNivelJugador() {
        return nivelJugador;
    }

    public void setNivelJugador(int nivelJugador) {
        this.nivelJugador = nivelJugador;
    }

    public int getNivelContrario() {
        return nivelContrario;
    }

    public void setNivelContrario(int nivelContrario) {
        this.nivelContrario = nivelContrario;
    }

    public int getVidaJugador() {
        return vidaJugador;
    }

    public void setVidaJugador(int vidaJugador) {
        this.vidaJugador = vidaJugador;
    }

    public int getVidaContrario() {
        return vidaContrario;
    }

    public void setVidaContrario(int vidaContrario) {
        this.vidaContrario = vidaContrario;
    }

    public int getAtaqueTotalJugador() {
        return ataqueTotalJugador;
    }

    public void setAtaqueTotalJugador(int ataqueTotalJugador) {
        this.ataqueTotalJugador = ataqueTotalJugador;
    }

    public int getAtaqueTotalContrario() {
        return ataqueTotalContrario;
    }

    public void setAtaqueTotalContrario(int ataqueTotalContrario) {
        this.ataqueTotalContrario = ataqueTotalContrario;
    }

    public int getDefensaTotalJugador() {
        return defensaTotalJugador;
    }

    public void setDefensaTotalJugador(int defensaTotalJugador) {
        this.defensaTotalJugador = defensaTotalJugador;
    }

    public int getDefensaTotalContrario() {
        return defensaTotalContrario;
    }

    public void setDefensaTotalContrario(int defensaTotalContrario) {
        this.defensaTotalContrario = defensaTotalContrario;
    }

    public int getPuntosJugador() {
        return puntosJugador;
    }

    public void setPuntosJugador(int puntosJugador) {
        this.puntosJugador = puntosJugador;
    }

    public int getPuntosContrario() {
        return puntosContrario;
    }

    public void setPuntosContrario(int puntosContrario) {
        this.puntosContrario = puntosContrario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idCombate;
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
        if (this.idCombate != other.idCombate) {
            return false;
        }
        return true;
    }
}
