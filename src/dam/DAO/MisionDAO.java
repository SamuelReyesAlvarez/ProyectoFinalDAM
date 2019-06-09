/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
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
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM Mision m "
                + "WHERE m.jugador = null"
        );

        return resultado.list();
    }

    public boolean comenzarMision(Mision mision) {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "UPDATE  FROM Mision m "
                + "WHERE m = " + mision
        ).uniqueResult(); // actualizar la mision asignada al jugador con la hora de inicio y de fin

        return resultado;
    }

    public int tiempoRestanteMision(Mision mision) {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object resultado = session.createQuery(
                "SELECT COALESCE(TIMESTAMPDIFF(SECOND, now(), m.fin), 0)"
                + "FROM Mision m "
                + "WHERE m = " + mision
                + " AND m.completada = 0"
        ).uniqueResult();

        return Integer.parseInt((String) resultado);
    }
}
