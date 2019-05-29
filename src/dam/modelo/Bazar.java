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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "registro_bazar")
public class Bazar implements Serializable {

    @Id
    @Column(name = "id_bazar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBazar;

    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Jugador comprador;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Jugador vendedor;

    @Column(name = "tipo_equipo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Inventario.TipoEquipo tipoEquipo;

    @Column(name = "nivel")
    @Min(1)
    @NotNull
    private int nivel;

    @Column(name = "potenciado")
    @NotNull
    private int potenciado;

    @Column(name = "precio")
    @NotNull
    private int precio;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fechaVenta;

    public Bazar() {
    }

    public Bazar(int idBazar, Jugador comprador, Jugador vendedor, Inventario.TipoEquipo tipoEquipo, int nivel, int potenciado, int precio, Date fechaVenta) {
        this.idBazar = idBazar;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.tipoEquipo = tipoEquipo;
        this.nivel = nivel;
        this.potenciado = potenciado;
        this.precio = precio;
        this.fechaVenta = fechaVenta;
    }

    public int getIdBazar() {
        return idBazar;
    }

    public void setIdBazar(int idBazar) {
        this.idBazar = idBazar;
    }

    public Jugador getComprador() {
        return comprador;
    }

    public void setComprador(Jugador comprador) {
        this.comprador = comprador;
    }

    public Jugador getVendedor() {
        return vendedor;
    }

    public void setVendedor(Jugador vendedor) {
        this.vendedor = vendedor;
    }

    public Inventario.TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(Inventario.TipoEquipo tipoEquipo) {
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

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.fechaVenta);
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
        final Bazar other = (Bazar) obj;
        if (!Objects.equals(this.fechaVenta, other.fechaVenta)) {
            return false;
        }
        return true;
    }
}
