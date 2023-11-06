package fantasy.item.generator.Data.DataStorage;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fantasy.item.generator.Weapon.MartialMeleeWeapon;
import fantasy.item.generator.Weapon.MartialRangeWeapon;
import fantasy.item.generator.Weapon.SimpleMeleeWeapon;
import fantasy.item.generator.Weapon.SimpleRangeWeapon;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            Configuration configuration = new Configuration();


            configuration.setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
            configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            configuration.setProperty("hibernate.connection.url", SqlLiteDBController.SQL_URL);
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");

            //Annotated Classes to use
            configuration.addAnnotatedClass(MartialMeleeWeapon.class);
            configuration.addAnnotatedClass(MartialRangeWeapon.class);
            configuration.addAnnotatedClass(SimpleMeleeWeapon.class);
            configuration.addAnnotatedClass(SimpleRangeWeapon.class);
            
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
