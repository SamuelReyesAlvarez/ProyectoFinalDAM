/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
import dam.modelo.Jugador;
import dam.modelo.Mision;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class MisionDAO {

    public List<Mision> obtenerParaTablon() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Mision m "
                + "WHERE m.jugador = null"
        );

        List<Mision> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }

    public List<Mision> obtenerMisionActiva(Jugador jugador) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Mision m "
                + "WHERE m.jugador = :jugador "
                + "AND m.completada = 0"
        );

        resultado.setEntity("jugador", jugador);

        List<Mision> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }
}
