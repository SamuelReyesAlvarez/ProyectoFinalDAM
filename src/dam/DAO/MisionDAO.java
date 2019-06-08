/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
import dam.modelo.Mision;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class MisionDAO {

    public int tiempoRestanteMision(Mision mision) {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "SELECT TIMESTAMPDIFF(SECOND, now(), m.fin) AS diferencia"
                + "FROM Mision m "
                + "WHERE m = " + mision);

        return resultado; // extraer dato de la consulta
    }
}
