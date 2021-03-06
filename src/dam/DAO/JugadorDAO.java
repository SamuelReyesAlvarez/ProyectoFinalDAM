/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

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
public class JugadorDAO {

    public List<Jugador> clasificacion() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.expAcumulada DESC");
        List<Jugador> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }

    public List<Jugador> filtroDesafio() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.puntosCombate DESC");
        List<Jugador> listado = resultado.list();
        session.getTransaction().commit();
        return listado;
    }

    public boolean comprobarNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "WHERE j.nombre = :nombre");
        resultado.setString("nombre", nombre);

        boolean existe = (resultado.list().isEmpty()) ? false : true;
        session.getTransaction().commit();
        return existe;
    }
}
