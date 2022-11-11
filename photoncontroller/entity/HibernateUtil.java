package photoncontroller.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Essa classe faz uma conexão com o banco de dados
public class HibernateUtil {

	private static SessionFactory factory = null;
	private static Configuration conf;

	private static SessionFactory buildSessionFactory(){
		try {
			conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			System.out.println("Configurou");

			factory = conf.buildSessionFactory();
			System.out.println("Contrui");

			return factory;

		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory(){

		if (factory == null)
			factory = buildSessionFactory();

			return factory;

	}
}