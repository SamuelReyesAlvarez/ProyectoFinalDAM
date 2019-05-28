/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Samuel
 */
public class GenericDAO<T> {

    /**
     * Guarda o actualiza el objeto pasado por parámetro y sus relaciones a
     * través de una transacción.
     *
     * @param entidad que se desea persistir
     */
    public void guardarActualizar(T entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(entidad);
        session.getTransaction().commit();
    }

    /**
     * Elimina el objeto de la base de datos y las relaciones que se hallan
     * especificado con DeleteOnCascade
     *
     * @param entidad que se desea borrar
     */
    public void borrar(T entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(entidad);
        session.getTransaction().commit();
    }

    public List<T> obtenerTodo(T entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("FROM " + entidad).list();
    }

    public T obtenerPorId(Class<T> objeto, int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (T) session.get(objeto, id);
    }

    public T obtenerPorId(Class<T> objeto, String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (T) session.get(objeto, id);
    }
}
