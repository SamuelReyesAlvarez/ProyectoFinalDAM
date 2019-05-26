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
 * @author Samuel
 */
public class JugadorDAO {

    public List obtenerOrdenadoPorExperiencia() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM jugador j, estadisticas e "
                + "WHERE j.idJugador = e.jugador"
                + "ORDER BY j.experiencia DESC");
        return resultado.list();
    }
}
