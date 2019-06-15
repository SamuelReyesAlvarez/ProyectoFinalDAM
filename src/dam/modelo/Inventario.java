/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "inventario")
public class Inventario implements Serializable {

    private static final int VALOR_BASE = 500;
    private static final int VALOR_POTENCIADO = 150;
    public static final int PROBABILIDAD_CREAR_EQUIPO = 5;

    public enum TipoEquipo {
        PENDIENTE, CASCO, COLLAR, PULSERA, CHALECO, CAPA, ESCUDO, PANTALON, ARMA, CINTURON, BOTAS, ANILLO
    }

    @Id
    @Column(name = "id_inventario")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idInventario;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    @Cascade(CascadeType.ALL)
    private Jugador jugador;

    @OneToOne
    @PrimaryKeyJoinColumn
    @NotNull
    private Estado estado;

    @Column(name = "tipo_equipo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoEquipo tipoEquipo;

    @Column(name = "nivel")
    @Min(1)
    @NotNull
    private int nivel;

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

    public Inventario(Jugador jugador, Estado estado, TipoEquipo tipoEquipo, int nivel, int potenciado, int precio, boolean equipado, boolean enVenta) {
        this.jugador = jugador;
        this.estado = estado;
        this.tipoEquipo = tipoEquipo;
        this.nivel = nivel;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        hash = 67 * hash + this.idInventario;
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
        if (this.idInventario != other.idInventario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipoEquipo.toString() + "\n N: " + nivel + " P: +" + potenciado + "\n" + estado.getTipoAtributo() + " +" + estado.getPotenciado();
    }

    public int getValor() {
        return (VALOR_BASE + (VALOR_POTENCIADO * potenciado)) * getNivel();
    }

    public int getCostePotenciar() {
        return VALOR_POTENCIADO * (potenciado + 1) * getNivel();
    }

    public void aumentarPotenciado() throws JuegoException {
        if (potenciado >= getNivel() * 10) {
            throw new JuegoException("Limite de potenciado alcanzado");
        }
        estado.setPotenciado((estado.getPotenciado() / (potenciado + 1)) * (potenciado + 2));
        potenciado++;
    }
}
