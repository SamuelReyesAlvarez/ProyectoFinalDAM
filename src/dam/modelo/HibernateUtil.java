package dam.modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Samuel Reyes Alvarez
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized void buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hibernate.current_session_context_class", "thread");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void openSessionAndBindToThread() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            System.out.println("ESTO NO DEBERIA SALIR");
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session != null) {
            session.close();
        }
    }

    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }
}
