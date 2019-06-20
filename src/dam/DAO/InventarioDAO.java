/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.MainApp;
import dam.modelo.HibernateUtil;
import dam.modelo.Inventario;
import dam.modelo.Jugador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class InventarioDAO {

    private MainApp mainApp;

    public InventarioDAO(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public List<Inventario> obtenerObjetosEnVenta(String tipo, String nivel, String potenciado) {
        String filtroTipo = "";
        String filtroNivel = "";
        String filtroPotenciado = "";

        if (!tipo.equals("Todos")) {
            filtroTipo = " AND i.tipoEquipo = '" + tipo + "'";
        }

        if (!nivel.equals("Todos")) {
            filtroNivel = " AND i.nivel = " + nivel;
        }

        if (!potenciado.equals("Todos")) {
            filtroPotenciado = " AND i.potenciado = " + potenciado;

        }

        mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Inventario i "
                + "WHERE i.enVenta = 1"
                + filtroTipo
                + filtroNivel
                + filtroPotenciado);
        List<Inventario> listado = resultado.list();
        session.getTransaction().commit();
        mainApp.cerrarSesion();
        return listado;
    }

    public List<Inventario> obtenerInventarioJugador(Jugador jugador) {
        mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Inventario i "
                + "WHERE i.jugador = " + jugador);
        List<Inventario> listado = resultado.list();
        session.getTransaction().commit();
        mainApp.cerrarSesion();
        return listado;
    }
}
