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

    public List clasificacion() {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.expAcumulada DESC");
        return resultado.list();
    }

    public List filtroDesafio() {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.estadisticas.puntosCombate DESC");
        return resultado.list();
    }

    public boolean comprobarNombre(String nombre) {
        Session session = new GenericDAO<>().comprobarConexion();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "WHERE j.nombre = " + nombre);
        return (resultado != null) ? true : false;
    }
}
