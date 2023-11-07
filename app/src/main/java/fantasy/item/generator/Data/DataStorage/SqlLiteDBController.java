package fantasy.item.generator.Data.DataStorage;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fantasy.item.generator.Data.DataHelpers.Rarity;
import fantasy.item.generator.Data.DataHandling.EnumMultation;
import fantasy.item.generator.Weapon.Weapon;
import fantasy.item.generator.Weapon.WeaponProperties;
import fantasy.item.generator.Weapon.WeaponsData;

public class SqlLiteDBController {
    public static final String DB_VERSION = "0.0.1";
    public static final String DB_FILE_NAME = "FantasyDBv" + DB_VERSION + ".db";
    public static final String DB_FILE_URL = getLocalizedFileString();
    static final String SQL_URL = "jdbc:sqlite:" + DB_FILE_URL;

    public static final String WEAPONS_DEFAULT_TABLE_NAME = "Weapons_Defaults";
    public static final String RARITY_DESCRIPTORS_TABLE_NAME = "Rarity_Descriptors_";

    private static SqlLiteDBController controller;

    public static SqlLiteDBController getInstance(){
        if (controller == null){
            controller = new SqlLiteDBController();
        } 
        return controller;
    }

    private SqlLiteDBController(){
        File sqlFile  = new File(DB_FILE_URL);
        if(!sqlFile.exists()){
            if(!initializeSQLDB()){
                return;
            }
        }

        loadInTables();

    }


    private void loadInTables() {
        String weaponsSql = "FROM " + WeaponProperties.class.getSimpleName();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query<WeaponProperties> queryProps = session.createQuery(weaponsSql, WeaponProperties.class);
            List<WeaponProperties> weaponProperties = queryProps.list();
            transaction.commit();
            EnumMultation.setWeaponsProps(weaponProperties);
        } catch (Exception e){
            System.out.println("Exception occured: " + e.getMessage());
        }

        HashMap<Rarity, List<String>> rarityDescriptions = new HashMap<>();
        try(Connection connection = DriverManager.getConnection(SQL_URL)){
            for(Rarity eValue : Rarity.values()){
                String sqlRarity = String.format("SELECT %s FROM %s%s", eValue.name(), RARITY_DESCRIPTORS_TABLE_NAME,eValue.name());
                ArrayList<String> descriptors = new ArrayList<>();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRarity);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    descriptors.add(resultSet.getString(eValue.name()));
                }
                rarityDescriptions.put(eValue, descriptors);
            } 

        } catch (SQLException e){
            System.out.println("Exception occured: " + e.getMessage());
        }
        
        EnumMultation.setRarityMapFromDB(rarityDescriptions);
    }

    private boolean initializeSQLDB() {
        try {
            Connection connection = DriverManager.getConnection(SQL_URL);
            if (connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("Database was successfully created! Driver name is: " + meta.getDriverName());
                initializeWeaponsTables();
                initializeRarityTables(connection);
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static void initializeWeaponsTypeTables(List<Weapon> weapons) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for(Weapon weapon: weapons){
                session.persist(weapon);
            }    
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Exception occured: " + e.getMessage());
        }
    }

    private void initializeRarityTables(Connection connection) throws SQLException{
        String preparedStatementString = "INSERT INTO %s (%s) VALUES(?)";

        for(Rarity eValue : Rarity.values()){
            String sqlStatement = "CREATE TABLE IF NOT EXISTS " + RARITY_DESCRIPTORS_TABLE_NAME + eValue.name() + "(\n";
            sqlStatement += " " + eValue.name() + " text PRIMARY KEY\n";
            sqlStatement += ");";
            Statement statement = connection.createStatement();
            statement.execute(sqlStatement);

            sqlStatement = String.format(preparedStatementString, RARITY_DESCRIPTORS_TABLE_NAME + eValue.name(), eValue.name());
            for(String descriptor : eValue.getDefaultDescriptors()){
                PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
                preparedStatement.setString(1, descriptor);
                preparedStatement.executeUpdate();
            }   
        }

        
    }


    private static void initializeWeaponsTables() throws SQLException{
        List<WeaponProperties> weapons = WeaponsData.getInstance().initializeWeaponsDB();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for(WeaponProperties weapon : weapons){
                session.persist(weapon);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Exception occured: " + e.getMessage());
        }
        // String sqlStatement = "CREATE TABLE IF NOT EXISTS " + WEAPONS_DEFAULT_TABLE_NAME + "(\n"
        //     +" weaponType text PRIMARY KEY,\n"
        //     + " cost integer,\n"
        //     + " costUnit text,\n"
        //     + " die text,\n"
        //     + " diceMulti integer,\n"
        //     + " damageType text,\n"
        //     + " weight double,\n"
        //     + " info text\n"
        //     + ");";
        // Statement statement = connection.createStatement();
        // statement.execute(sqlStatement);

        // sqlStatement = String.format(weapons.get(0).getSQLInsertString(), WEAPONS_DEFAULT_TABLE_NAME);
        // PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            // preparedStatement.setString(1, weapon.getWeaponType());
            // preparedStatement.setInt(2, weapon.getCost());
            // preparedStatement.setString(3, weapon.getCostUnit());
            // preparedStatement.setString(4, weapon.getDieString());
            // preparedStatement.setInt(5, weapon.getDiceMulti());
            // preparedStatement.setString(6, weapon.getDamageTypeString());
            // preparedStatement.setDouble(7, weapon.getWeight());
            // preparedStatement.setString(8, weapon.getInfo());
            // preparedStatement.executeUpdate();
    }


    private static String getLocalizedFileString() {
        String local = System.getProperty("user.dir");
        File storage = new File(local, "Fantasy_DB");
        if(!storage.exists()){
            try{
                storage.mkdirs();
            } catch (Exception e) {
                System.out.println("A problem occured while attempting to create file at: " + storage.getAbsolutePath() + "\n" + e.getMessage());
            }
        }
        return storage.getAbsolutePath() + "/" + DB_FILE_NAME;
    }


    public void addListEntryToTable(String tableName, String columnName, Object value){
        String sqlInsertStatement = String.format("INSERT INTO %s (%s) VALUES(?)", tableName, columnName);
        
        String sqlTypeStatement = String.format("SELECT * FROM %s", tableName);

        try {
            Connection connection = DriverManager.getConnection(SQL_URL);
            if (connection != null){
                Statement statement = connection.createStatement();
                ResultSetMetaData metaData = statement.executeQuery(sqlTypeStatement).getMetaData();
                Integer columnType = null;
                for(int i = 0; i < metaData.getColumnCount(); i++){
                    if(metaData.getColumnName(i).equals(columnName)){
                        columnType = metaData.getColumnType(i);
                        break;
                    }
                }

                if(columnType == null){
                    throw new SQLException("Error occured could not find column: " + columnName + " in table: " + tableName);
                }
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertStatement);
                //Add more types as more types are added to db
                switch(columnType){
                    case Types.DOUBLE:
                        preparedStatement.setDouble(1, (double) value);
                        break;
                    case Types.INTEGER:
                        preparedStatement.setInt(1, (int) value);
                        break;
                    default:
                        preparedStatement.setString(1, (String) value);
                        break;
                }

                preparedStatement.executeUpdate();



            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeListEntryFromTable(String tableName, String columnName, Object value){
        String sqlRemoveStatement = String.format("DELETE FROM %s WHERE %s = ?", tableName, columnName);

        try {
            Connection connection = DriverManager.getConnection(SQL_URL);
            if (connection != null){

                PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveStatement);
                preparedStatement.setString(1, (String) value);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
