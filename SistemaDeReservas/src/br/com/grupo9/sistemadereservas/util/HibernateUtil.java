package br.com.grupo9.sistemadereservas.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Classe responssável por disponibilizar as seções do Hibernate para realizarmos as requisições ao banco de dados.
 *
 * @author Marcos
 */

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;

    static{
    	try{
    	Configuration configuration = new Configuration();
    	configuration.configure();
    	
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
    	
    	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	}catch(Throwable ex){
    		throw new ExceptionInInitializerError(ex);
    	}
    }
    
    public static Session getSession(){
    	return sessionFactory.openSession();
    }
}
