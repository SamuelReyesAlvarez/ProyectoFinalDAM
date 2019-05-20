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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "acceso")
public class Acceso implements Serializable {

    @Id
    @Column(name = "correo")
    @Size(min = 5, max = 150)
    @NotNull
    private String correo;

    @Column(name = "clave")
    @Size(min = 8, max = 150)
    @NotNull
    private String clave;

    @OneToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    public Acceso() {
    }

    public Acceso(String correo, String clave, Jugador jugador) {
        this.correo = correo;
        this.clave = clave;
        this.jugador = jugador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.correo);
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
        final Acceso other = (Acceso) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }
}