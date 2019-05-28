/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Samuel
 */
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable, Comparable<Jugador> {

    private static final int VIDA_BASE = 1000;
    private static final int ATAQUE_MIN_BASE = 100;
    private static final int ATAQUE_MAX_BASE = 120;
    private static final int DEFENSA_MIN_BASE = 0;
    private static final int DEFENSA_MAX_BASE = 0;
    private static final int PUNTOS_ATR_NUEVO_NIVEL = 3;
    private static final int VALOR_FUERZA = 2;
    private static final int VALOR_ARMADURA = 2;
    private static final int VALOR_DESTREZA = 1;
    private static final int VALOR_CONSTITUCION = 20;

    @Id
    @Column(name = "id_jugador")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idJugador;

    @Column(name = "nombre")
    @Size(min = 3, max = 15)
    @NotNull
    private String nombre;

    @Column(name = "nivel")
    @Min(1)
    @NotNull
    private int nivel;

    @Column(name = "exp_acumulada")
    @Min(0)
    @NotNull
    private int expAcumulada;

    @Column(name = "puntos_no_usados")
    @Min(0)
    @NotNull
    private int puntosNoUsados;

    @Column(name = "oro_actual")
    @Min(0)
    @NotNull
    private int oroActual;

    @OneToMany(mappedBy = "jugador")
    @Cascade(CascadeType.ALL)
    private Set<Estado> estadoJugador;

    @OneToMany(mappedBy = "jugador")
    @Cascade(CascadeType.ALL)
    private Set<Inventario> equipoJugador;

    @OneToMany(mappedBy = "jugador")
    @Cascade(CascadeType.ALL)
    private List<Mision> tareaActiva;

    /*
    @OneToMany(mappedBy = "jugador")
    @Cascade(CascadeType.ALL)
    private List<Bazar> ventas;

    @OneToMany(mappedBy = "jugador")
    @Cascade(CascadeType.ALL)
    private List<Combate> combates;
     */
    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, int nivel, int expAcumulada, int puntosNoUsados, int oroActual, Set<Estado> estadoJugador, Set<Inventario> equipoJugador, List<Mision> tareaActiva, List<Bazar> ventas, List<Combate> combates) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.nivel = nivel;
        this.expAcumulada = expAcumulada;
        this.puntosNoUsados = puntosNoUsados;
        this.oroActual = oroActual;
        this.estadoJugador = estadoJugador;
        this.equipoJugador = equipoJugador;
        this.tareaActiva = tareaActiva;
        //this.ventas = ventas;
        //this.combates = combates;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExpAcumulada() {
        return expAcumulada;
    }

    public void setExpAcumulada(int expAcumulada) {
        this.expAcumulada = expAcumulada;
    }

    public int getPuntosNoUsados() {
        return puntosNoUsados;
    }

    public void setPuntosNoUsados(int puntosNoUsados) {
        this.puntosNoUsados = puntosNoUsados;
    }

    public int getOroActual() {
        return oroActual;
    }

    public void setOroActual(int oroActual) {
        this.oroActual = oroActual;
    }

    public Set<Estado> getEstadoJugador() {
        return estadoJugador;
    }

    public void setEstadoJugador(HashSet<Estado> estadoJugador) {
        this.estadoJugador = estadoJugador;
    }

    public Set<Inventario> getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(HashSet<Inventario> equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    public List<Mision> getTareaActiva() {
        return tareaActiva;
    }

    public void setTareaActiva(List<Mision> tareaActiva) {
        this.tareaActiva = tareaActiva;
    }

    /*
    public List<Bazar> getVentas() {
        return ventas;
    }

    public void setVentas(List<Bazar> ventas) {
        this.ventas = ventas;
    }

    public List<Combate> getCombate() {
        return combates;
    }

    public void setCombate(List<Combate> combate) {
        this.combates = combate;
    }
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
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
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public int getExpNivel(int nivel) {
        return (int) (Math.pow(nivel, 3) + Math.pow(nivel, 2));
    }

    public int getValorAtributo(Estado.TipoAtributo tipo) {
        int potenciando = 0;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == tipo) {
                potenciando = estado.getPotenciado();
            }
        }
        return potenciando;
    }

    public int getVidaMax() {
        int vidaTotal = VIDA_BASE;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == Estado.TipoAtributo.AGUA) {
                vidaTotal += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.CONSTITUCION) {
                vidaTotal += estado.getPotenciado() * VALOR_CONSTITUCION;
            }
        }
        return vidaTotal;
    }

    public int getAtaqueMin() {
        int ataqueMin = ATAQUE_MIN_BASE;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == Estado.TipoAtributo.FUEGO) {
                ataqueMin += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.FUERZA) {
                ataqueMin += estado.getPotenciado() * VALOR_FUERZA;
            }
        }
        return ataqueMin;
    }

    public int getAtaqueMax() {
        int ataqueMax = ATAQUE_MAX_BASE;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == Estado.TipoAtributo.FUEGO) {
                ataqueMax += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.VIENTO) {
                ataqueMax += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.FUERZA) {
                ataqueMax += estado.getPotenciado() * VALOR_FUERZA;
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.DESTREZA) {
                ataqueMax += estado.getPotenciado() * VALOR_DESTREZA;
            }
        }
        return ataqueMax;
    }

    public int getDefensaMin() {
        int defensaMin = DEFENSA_MIN_BASE;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == Estado.TipoAtributo.TIERRA) {
                defensaMin += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.ARMADURA) {
                defensaMin += estado.getPotenciado() * VALOR_ARMADURA;
            }
        }
        return defensaMin;
    }

    public int getDefensaMax() {
        int defensaMax = DEFENSA_MAX_BASE;
        for (Estado estado : estadoJugador) {
            if (estado.getTipoAtributo() == Estado.TipoAtributo.TIERRA) {
                defensaMax += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.VIENTO) {
                defensaMax += estado.getPotenciado();
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.ARMADURA) {
                defensaMax += estado.getPotenciado() * VALOR_ARMADURA;
            }
            if (estado.getTipoAtributo() == Estado.TipoAtributo.DESTREZA) {
                defensaMax += estado.getPotenciado() * VALOR_DESTREZA;
            }
        }
        return defensaMax;
    }

    public void subirExpAcumulada(int exp) {
        this.expAcumulada += exp;

        while (this.expAcumulada >= getExpNivel(this.nivel)) {
            this.nivel++;
            this.puntosNoUsados += PUNTOS_ATR_NUEVO_NIVEL;
        }
    }

    @Override
    public int compareTo(Jugador o) {
        if (this.getExpAcumulada() > o.getExpAcumulada()) {
            return -1;
        } else if (this.getExpAcumulada() < o.getExpAcumulada()) {
            return 1;
        } else {
            return 0;
        }
    }
}
