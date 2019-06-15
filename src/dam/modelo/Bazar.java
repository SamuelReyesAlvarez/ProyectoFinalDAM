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
import javax.persistence.Table;
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

    @Column(name = "tipo_atributo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Estado.TipoAtributo tipoAtributo;

    @Column(name = "potenciado_atributo")
    @NotNull
    private int potenciadoAtributo;

    @Column(name = "precio")
    @NotNull
    private int precio;

    public Bazar() {
    }

    public Bazar(Jugador comprador, Jugador vendedor, Inventario.TipoEquipo tipoEquipo, int nivel, int potenciado, Estado.TipoAtributo tipoAtributo, int potenciadoAtributo, int precio) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.tipoEquipo = tipoEquipo;
        this.nivel = nivel;
        this.potenciado = potenciado;
        this.tipoAtributo = tipoAtributo;
        this.potenciadoAtributo = potenciadoAtributo;
        this.precio = precio;
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

    public Estado.TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(Estado.TipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public int getPotenciadoAtributo() {
        return potenciadoAtributo;
    }

    public void setPotenciadoAtributo(int potenciadoAtributo) {
        this.potenciadoAtributo = potenciadoAtributo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idBazar;
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
        if (this.idBazar != other.idBazar) {
            return false;
        }
        return true;
    }
}
