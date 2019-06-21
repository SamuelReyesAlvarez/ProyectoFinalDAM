/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.Acceso;
import dam.modelo.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class AccesoDAO {

    public Acceso comprobarCuenta(String correo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Acceso a "
                + "WHERE correo = :correo");

        resultado.setString("correo", correo);

        if (resultado.list().size() > 0) {
            Acceso acceso = (Acceso) resultado.list().get(0);
            session.getTransaction().commit();
            return acceso;
        } else {
            session.getTransaction().rollback();
            return null;
        }
    }
}
