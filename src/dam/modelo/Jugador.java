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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable, Comparable<Jugador> {

    public static final int VIDA_BASE = 1000;
    public static final int ATAQUE_MIN_BASE = 100;
    public static final int ATAQUE_MAX_BASE = 120;
    public static final int DEFENSA_MIN_BASE = 0;
    public static final int DEFENSA_MAX_BASE = 0;
    public static final int PUNTOS_ATR_NUEVO_NIVEL = 3;
    public static final int VALOR_FUERZA = 2;
    public static final int VALOR_ARMADURA = 2;
    public static final int VALOR_DESTREZA = 1;
    public static final int VALOR_CONSTITUCION = 20;

    @Id
    @Column(name = "id_jugador")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idJugador;

    @Column(name = "nombre")
    @Pattern(regexp = "[A-Za-z]{3,15}")
    @NotNull
    private String nombre;

    @Column(name = "imagen")
    @Size(max = 250)
    @NotNull
    private String imagen;

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

    @OneToOne
    @PrimaryKeyJoinColumn
    @Cascade(CascadeType.ALL)
    @NotNull
    private Estadisticas estadisticas;

    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, String imagen, int nivel, int expAcumulada, int puntosNoUsados, int oroActual, Set<Estado> estadoJugador, Set<Inventario> equipoJugador, List<Mision> tareaActiva, Estadisticas estadisticas) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
        this.expAcumulada = expAcumulada;
        this.puntosNoUsados = puntosNoUsados;
        this.oroActual = oroActual;
        this.estadoJugador = estadoJugador;
        this.equipoJugador = equipoJugador;
        this.tareaActiva = tareaActiva;
        this.estadisticas = estadisticas;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

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

    @Override
    public int compareTo(Jugador o) {
        /*
        if (this.getExpAcumulada() > o.getExpAcumulada()) {
            return -1;
        } else if (this.getExpAcumulada() < o.getExpAcumulada()) {
            return 1;
        } else {
            return 0;
        }
         */
        return Integer.compare(this.expAcumulada, o.expAcumulada);
    }

    @Override
    public String toString() {
        StringBuilder listaEstado = new StringBuilder();
        for (Estado estado : estadoJugador) {
            listaEstado.append(estado.getTipoAtributo() + " " + estado.getPotenciado() + "\n");
        }

        return nombre + ", nivel " + nivel
                + "\nvida " + getVidaMax()
                + "\nataque " + getAtaqueMin() + " - " + getAtaqueMax()
                + "\ndefensa " + getDefensaMin() + " - " + getDefensaMax()
                + "\n" + listaEstado.toString();
    }

    public int compararCombates(Jugador o) {
        return Integer.compare(this.getEstadisticas().getPuntosCombate(), o.getEstadisticas().getPuntosCombate());
    }

    public int getExpAcumuladaNivelActual() {
        return getExpAcumulada() - (int) (Math.pow(nivel - 1, 3) + Math.pow(nivel - 1, 2));
    }

    public int getExpTotalNivelActual() {
        return (int) (Math.pow(nivel, 3) + Math.pow(nivel, 2)) - (int) (Math.pow(nivel - 1, 3) + Math.pow(nivel - 1, 2));
    }

    public double getProgresoExp() {
        return (double) (getExpAcumuladaNivelActual() / getExpTotalNivelActual());
    }

    public int getExpSiguienteNivel() {
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

        while (getExpAcumulada() >= getExpSiguienteNivel()) {
            this.nivel++;
            this.puntosNoUsados += PUNTOS_ATR_NUEVO_NIVEL;
        }
    }

    public void mejorarAtributo(Estado.TipoAtributo tipo) {
        for (Estado estado : getEstadoJugador()) {
            if (estado.getTipoAtributo() == tipo) {
                estado.setPotenciado(estado.getPotenciado() + 1);
                this.setPuntosNoUsados(this.getPuntosNoUsados() - 1);
            }
        }
    }

    public Inventario getEquipo(Inventario patron) {
        for (Inventario equipo : equipoJugador) {
            if (equipo.isEquipado() == patron.isEquipado()
                    && equipo.isEnVenta() == patron.isEnVenta()
                    && equipo.getTipoEquipo() == patron.getTipoEquipo()
                    && equipo.getNivel() == patron.getNivel()
                    && equipo.getPotenciado() == patron.getPotenciado()
                    && equipo.getEstado().getTipoAtributo() == patron.getEstado().getTipoAtributo()
                    && equipo.getEstado().getPotenciado() == patron.getEstado().getPotenciado()) {
                return equipo;
            }
        }
        return null;
    }

    public Estado getElementoDominante() {
        Estado mayor = new Estado(0, null, null, Integer.MIN_VALUE);

        for (Estado estado : estadoJugador) {
            switch (estado.getTipoAtributo()) {
                case TIERRA:
                    if (mayor.getPotenciado() < estado.getPotenciado()) {
                        mayor = estado;
                    }
                    break;
                case AGUA:
                    if (mayor.getPotenciado() < estado.getPotenciado()) {
                        mayor = estado;
                    }
                    break;
                case FUEGO:
                    if (mayor.getPotenciado() < estado.getPotenciado()) {
                        mayor = estado;
                    }
                    break;
                case VIENTO:
                    if (mayor.getPotenciado() < estado.getPotenciado()) {
                        mayor = estado;
                    }
                    break;
            }
        }

        return mayor;
    }

    public Estado getElementoDefensa(Estado ataque) {
        switch (ataque.getTipoAtributo()) {
            case TIERRA:
                for (Estado estado : estadoJugador) {
                    if (estado.getTipoAtributo() == Estado.TipoAtributo.AGUA) {
                        return estado;
                    }
                }
            case AGUA:
                for (Estado estado : estadoJugador) {
                    if (estado.getTipoAtributo() == Estado.TipoAtributo.FUEGO) {
                        return estado;
                    }
                }
                break;
            case FUEGO:
                for (Estado estado : estadoJugador) {
                    if (estado.getTipoAtributo() == Estado.TipoAtributo.VIENTO) {
                        return estado;
                    }
                }
                break;
            case VIENTO:
                for (Estado estado : estadoJugador) {
                    if (estado.getTipoAtributo() == Estado.TipoAtributo.TIERRA) {
                        return estado;
                    }
                }
                break;
        }
        return null;
    }

    public void actualizarOroActual(int cantidad) {
        this.oroActual += cantidad;
    }
}
