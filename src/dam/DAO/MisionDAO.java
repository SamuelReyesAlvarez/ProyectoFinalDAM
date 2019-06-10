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

    public Mision comenzarMision(Mision mision) {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery(
                "UPDATE Mision m "
                + "SET m.inicio = NOW(), "
                + "SET m.fin = (NOW() + INTERVAL " + mision.getDuracion() + " HOUR)"
                + "WHERE m = " + mision
        );
        session.getTransaction().commit();
        return (Mision) new GenericDAO().obtenerPorId(Mision.class, mision.getIdMision());
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
