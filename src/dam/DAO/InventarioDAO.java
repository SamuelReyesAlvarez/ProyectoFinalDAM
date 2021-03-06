/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

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

    public List<Inventario> obtenerObjetosEnVenta(String tipo, String nivel, String potenciado) {
        String filtroTipo = "";
        String filtroNivel = "";
        String filtroPotenciado = "";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Inventario i "
                + "WHERE i.enVenta = 1"
                + filtroTipo
                + filtroNivel
                + filtroPotenciado);

        if (!tipo.equals("Todos")) {
            filtroTipo = " AND i.tipoEquipo = :tipo";
            resultado.setString("tipo", tipo);
        }

        if (!nivel.equals("Todos")) {
            filtroNivel = " AND i.nivel = :nivel";
            resultado.setString("nivel", nivel);
        }

        if (!potenciado.equals("Todos")) {
            filtroPotenciado = " AND i.potenciado = :potenciado";
            resultado.setString("potenciado", potenciado);

        }

        List<Inventario> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }

    public List<Inventario> obtenerInventarioJugador(Jugador jugador) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Inventario i "
                + "WHERE i.jugador = :jugador");
        resultado.setEntity("jugador", jugador);

        List<Inventario> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }
}
