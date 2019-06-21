/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.Estado;
import dam.modelo.HibernateUtil;
import dam.modelo.Jugador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class EstadoDAO {

    public List<Estado> obtenerEstadoJugador(Jugador jugador) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Estado e "
                + "WHERE e.jugador = :jugador");
        resultado.setEntity("jugador", jugador);

        List<Estado> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }
}
