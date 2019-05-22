/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "atributo")
public class Atributo implements Serializable {

    public enum TipoAtributo {
        FUERZA, DESTREZA, ARMADURA, CONSTITUCION, TIERRA, AGUA, FUEGO, VIENTO
    }

    @Id
    @Column(name = "id_atributo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAtributo;

    @Column(name = "tipo_atributo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoAtributo tipoAtributo;

    @OneToMany(mappedBy = "atributo")
    @Cascade(CascadeType.ALL)
    private Set<Estado> atributoJugador;

    public Atributo() {
    }

    public Atributo(int idAtributo, TipoAtributo tipoAtributo, HashSet<Estado> atributoJugador) {
        this.idAtributo = idAtributo;
        this.tipoAtributo = tipoAtributo;
        this.atributoJugador = atributoJugador;
    }

    public int getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(int idAtributo) {
        this.idAtributo = idAtributo;
    }

    public TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(TipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public Set<Estado> getAtributoJugador() {
        return atributoJugador;
    }

    public void setAtributoJugador(HashSet<Estado> atributoJugador) {
        this.atributoJugador = atributoJugador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.tipoAtributo);
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
        final Atributo other = (Atributo) obj;
        if (this.tipoAtributo != other.tipoAtributo) {
            return false;
        }
        return true;
    }
}
