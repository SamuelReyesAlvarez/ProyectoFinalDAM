/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.MainApp;
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

    private MainApp mainApp;

    public JugadorDAO(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public List<Jugador> clasificacion() {
        mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.expAcumulada DESC");
        List<Jugador> listado = resultado.list();
        session.getTransaction().commit();
        mainApp.cerrarSesion();
        return listado;
    }

    public List<Jugador> filtroDesafio() {
        mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "ORDER BY j.estadisticas.puntosCombate DESC");
        List<Jugador> listado = resultado.list();
        session.getTransaction().commit();
        mainApp.cerrarSesion();
        return listado;
    }

    public boolean comprobarNombre(String nombre) {
        mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Jugador j "
                + "WHERE j.nombre = " + nombre);
        boolean existe = (resultado != null) ? true : false;
        session.getTransaction().commit();
        mainApp.cerrarSesion();
        return existe;
    }
}
