/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.DAO;

import dam.modelo.HibernateUtil;
import dam.modelo.JuegoException;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hibernate.Session;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class GenericDAO<T> {

    /**
     * Guarda o actualiza el objeto pasado por parámetro y sus relaciones a
     * través de una transacción.
     *
     * @param entidad que se desea persistir
     */
    public void guardarActualizar(T entidad) throws JuegoException {
        StringBuilder mensajeError = new StringBuilder();
        String mensajeValidacion;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(entidad);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
                if (constraintViolation.getPropertyPath().toString().contains("correo")) {
                    mensajeValidacion = "formato incorrecto";
                } else {
                    mensajeValidacion = constraintViolation.getMessage();
                }
                mensajeError.append(constraintViolation.getPropertyPath() + ": " + mensajeValidacion + "\n");
            }
            throw new JuegoException(mensajeError.toString());
        }
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

    public List<T> obtenerTodos(Class<T> entidad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<T> resultado = session.createQuery("FROM " + entidad).list();
        session.getTransaction().commit();
        return resultado;
    }

    public T obtenerPorId(Class<T> objeto, int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        T resultado = (T) session.get(objeto, id);
        session.getTransaction().commit();
        return resultado;
    }

    public T obtenerPorId(Class<T> objeto, String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        T resultado = (T) session.get(objeto, id);
        session.getTransaction().commit();
        return resultado;
    }
}
