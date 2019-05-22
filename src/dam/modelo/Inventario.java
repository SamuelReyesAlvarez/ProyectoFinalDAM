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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

    private static final int VALOR_BASE = 500;
    private static final int VALOR_POTENCIADO = 150;
    private static final int PROBABILIDAD_CREAR_EQUIPO = 5;

    @Id
    @Column(name = "id_inventario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idInventario;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @Column(name = "potenciado")
    @Min(0)
    @NotNull
    private int potenciado;

    @Column(name = "precio")
    @Min(0)
    private int precio;

    @Column(name = "equipado")
    @Type(type = "boolean")
    @NotNull
    private boolean equipado;

    @Column(name = "en_venta")
    @Type(type = "boolean")
    @NotNull
    private boolean enVenta;

    public Inventario() {
    }

    public Inventario(int idInventario, Jugador jugador, Equipo equipo, int potenciado, int precio, boolean equipado, boolean enVenta) {
        this.idInventario = idInventario;
        this.jugador = jugador;
        this.equipo = equipo;
        this.potenciado = potenciado;
        this.precio = precio;
        this.equipado = equipado;
        this.enVenta = enVenta;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPotenciado() {
        return potenciado;
    }

    public void setPotenciado(int potenciado) {
        this.potenciado = potenciado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
        if (equipado) {
            setEnVenta(false);
        }
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
        if (enVenta) {
            setEquipado(false);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.jugador);
        hash = 83 * hash + Objects.hashCode(this.equipo);
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
        final Inventario other = (Inventario) obj;
        if (!Objects.equals(this.jugador, other.jugador) && !Objects.equals(this.equipo, other.equipo)) {
            return false;
        }
        return true;
    }

    public int getValor() {
        return (VALOR_BASE + (VALOR_POTENCIADO * potenciado)) * equipo.getNivel();
    }

    public int getCostePotenciar() {
        return VALOR_POTENCIADO * (potenciado + 1) * equipo.getNivel();
    }

    public void aumentarPotenciado() throws JuegoException {
        if (potenciado == equipo.getNivel() * 10) {
            throw new JuegoException("Limite de potenciado alcanzado");
        }
        potenciado++;
    }
}
