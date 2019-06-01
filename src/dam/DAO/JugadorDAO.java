/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class JugadorDAO {

    public List obtenerClasificacion() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM Jugador j, Estadisticas e "
                + "WHERE j.idJugador = e.jugador "
                + "ORDER BY j.expAcumulada DESC");
        return resultado.list();
    }
}
