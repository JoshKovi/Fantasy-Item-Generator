/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package fantasy.item.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.io.Files;

import fantasy.item.generator.Data.DataHelpers.DamageType;
import fantasy.item.generator.Data.DataHelpers.Rarity;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import fantasy.item.generator.Data.DataStorage.SqlLiteDBController;
import fantasy.item.generator.Weapon.AbstractWeapon;
import fantasy.item.generator.Weapon.WeaponGenerator;
import fantasy.item.generator.Weapon.WeaponProperties;

public class MainApp {

    public static void main(String[] args) {

        SqlLiteDBController dbController = SqlLiteDBController.getInstance();

        
        List<AbstractWeapon> weapons = new ArrayList<>();
        WeaponGenerator generator = new WeaponGenerator();
        int count = 0; 
        while (count < 5000){
            int picker = new Random().nextInt(Rarity.values().length);
            AbstractWeapon weapon = generator.generateWeapon(Rarity.values()[picker], null);
            SqlLiteDBController.initializeWeaponsTypeTables(weapon);
            weapons.add(weapon);
            count++;
        }




        /* This is code for building a big ole CSV, keep for testing for now */

        // WeaponGenerator generator = new WeaponGenerator();

        // List<AbstractWeapon> weapons = new ArrayList<>();

        // int count = 0;

        // while(count < 5000){
        //     weapons.add(generator.generateWeapon());
        //     count++;
        // }

        // List<WeaponProperties> weaponsProps = generator.getWeaponsData().getWeaponProperties();

        // while(count < 10000){
        //     int picker = new Random().nextInt(weaponsProps.size());
        //     int addPicker = new Random().nextInt(DamageType.values().length);
        //     weapons.add(generator.generateWeapon(null, null, weaponsProps.get(picker).getWeaponType().replaceAll(" ", "_"), DamageType.values()[addPicker].name()));
        //     count++;
        // }
        // File file = new File("C:/Users/joshu/Documents/Fantasy Item Generator/Extra Stuff", "output.csv");
        // try{
        //     String fileOutput = "Name,Tier,Main Dice,Main Damage Type,Extra Dice,Extra Damage Type,Cost: GP, Average Hit Per 10k\n";
        //     for(AbstractWeapon weapon : weapons){
        //         fileOutput += weapon.getName() + "," + weapon.getRarity().name()
        //         + "," + weapon.getMainMutliple() + weapon.getMainDieString()
        //         + "," + weapon.getMainDamageString() + "," + weapon.getAdditionalMutliple() + weapon.getAdditionalDieString()
        //         + "," + weapon.getAdditionalDamageType() + "," + String.format("%.2f", weapon.getGpValue()) 
        //         + "," + weapon.getDamageAveragePer1k() + "\n";
        //     }
        //     Files.write(fileOutput.getBytes(), file);
        // } catch (Exception e){
        //     System.out.println(e.getMessage());
        // }




        return;
    }
}
