package fantasy.item.generator.Data.DataStorage;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fantasy.item.generator.Weapon.Weapon;
import fantasy.item.generator.Weapon.WeaponProperties;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration();


            configuration.setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
            configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            configuration.setProperty("hibernate.connection.url", SqlLiteDBController.SQL_URL);
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            //Annotated Classes to use
            configuration.addAnnotatedClass(Weapon.class);
            configuration.addAnnotatedClass(WeaponProperties.class);

            
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Error In BuildSession: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
