/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.MainApp;
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

    private MainApp mainApp;

    public MisionDAO(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public List<Mision> obtenerParaTablon() {
        //mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "FROM Mision m "
                + "WHERE m.jugador = null"
        );

        List<Mision> listado = resultado.list();
        session.getTransaction().commit();
        //mainApp.cerrarSesion();
        return listado;
    }

    /*
    public Mision comenzarMision(Mision mision) {
        //mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "UPDATE Mision m "
                + "SET m.inicio = NOW(), "
                + "SET m.fin = (NOW() + INTERVAL :duracion MINUTE) "
                + "WHERE m = :mision"
        );

        resultado.setInteger("duracion", mision.getDuracion());
        resultado.setEntity("mision", mision);

        session.getTransaction().commit();
        //mainApp.cerrarSesion();
        return (Mision) new GenericDAO(mainApp).obtenerPorId(Mision.class, mision.getIdMision());
    }

    public int tiempoRestanteMision(Mision mision) {
        //mainApp.configurarYAbrirSesion();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query resultado = session.createQuery(
                "SELECT COALESCE(TIMESTAMPDIFF(SECOND, now(), m.fin), 0)"
                + "FROM Mision m "
                + "WHERE m = :mision "
                + "AND m.completada = 0"
        );

        resultado.setEntity("mision", mision);

        int restante = Integer.parseInt(resultado.getQueryString());
        session.getTransaction().commit();
        //mainApp.cerrarSesion();
        return restante;
    }
     */
}
