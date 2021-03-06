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
import javax.persistence.Table;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "estadisticas")
public class Estadisticas implements Serializable {

    @Id
    @Column(name = "id_estadisticas")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEstadisticas;

    @Column(name = "victorias")
    private int victorias;

    @Column(name = "derrotas")
    private int derrotas;

    @Column(name = "ataque_total")
    private int totalAtaque;

    @Column(name = "defensa_total")
    private int totalDefensa;

    @Column(name = "misiones")
    private int misionesCompletadas;

    public Estadisticas() {
    }

    public Estadisticas(int victorias, int derrotas, int totalAtaque, int totalDefensa, int misionesCompletadas) {
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.totalAtaque = totalAtaque;
        this.totalDefensa = totalDefensa;
        this.misionesCompletadas = misionesCompletadas;
    }

    public int getIdEstadisticas() {
        return idEstadisticas;
    }

    public void setIdEstadisticas(int idEstadisticas) {
        this.idEstadisticas = idEstadisticas;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getTotalAtaque() {
        return totalAtaque;
    }

    public void setTotalAtaque(int totalAtaque) {
        this.totalAtaque = totalAtaque;
    }

    public int getTotalDefensa() {
        return totalDefensa;
    }

    public void setTotalDefensa(int totalDefensa) {
        this.totalDefensa = totalDefensa;
    }

    public int getMisionesCompletadas() {
        return misionesCompletadas;
    }

    public void setMisionesCompletadas(int misionesCompletadas) {
        this.misionesCompletadas = misionesCompletadas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idEstadisticas;
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
        final Estadisticas other = (Estadisticas) obj;
        if (this.idEstadisticas != other.idEstadisticas) {
            return false;
        }
        return true;
    }

    public void aumentarVictorias() {
        this.victorias++;
    }

    public void aumentarDerrotas() {
        this.derrotas++;
    }

    public void aumentarTotalAtaque(int cantidad) {
        this.totalAtaque += cantidad;
    }

    public void aumentarTotalDefensa(int cantidad) {
        this.totalDefensa += cantidad;
    }

    public void aumentarMisiones() {
        this.misionesCompletadas++;
    }
}
