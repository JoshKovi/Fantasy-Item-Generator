package fantasy.item.generator.Data.Items.Weapon;

import java.util.ArrayList;
import java.util.List;

import fantasy.item.generator.Data.Attributes.Dice;
import fantasy.item.generator.Data.Attributes.DataHelpers.DamageType;


/**
 * TODO: Replace this with some DB backing and or just a json file or some
 * underlining mutable data source.
 * /* Honestly, when this is done the entire Base level information should be
 * contained for simplier building stuff.
 */

public class WeaponsData {
    public static List<WeaponProperties> weaponsProps;
    private static List<Weapon> weapons;
    private static WeaponsData weaponsDataSingleton;

    private WeaponsData() {
        weaponsProps = initializeWeaponsDB();
    }

    public static WeaponsData getInstance(){
        if (weaponsDataSingleton == null){
            weaponsDataSingleton = new WeaponsData();
        }
        return weaponsDataSingleton;
    }

    public List<WeaponProperties> initialDataLoadFromCSV(){
        // TODO: Eventually this should be prefered over the InitializeWeaponsDB hard coded defaults.
        String localDir = System.getProperty("user.dir");
        System.out.println("Local directory: " + localDir);

        return null;
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public void addWeaponProp(WeaponProperties weaponProp){
        weaponsProps.add(weaponProp);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public Weapon getWeaponByIndex(int i){
        return weapons.get(i);
    }

    public Weapon getWeaponByName(String name) {
        return weapons.stream()
            .filter(weapon -> weapon.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public void setWeaponsList(List<Weapon> weaponsList){
        weapons = weaponsList;
    }

    public List<WeaponProperties> getWeaponProperties() {
        return weaponsProps;
    }

    public WeaponProperties getWeaponPropsByIndex(int i){
        return weaponsProps.get(i);
    }

    public WeaponProperties getWeaponPropsByName(String name) {
        return weaponsProps.stream()
            .filter(weapon -> weapon.getWeaponType().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    public void setWeaponsPropsList(List<WeaponProperties> weaponsPropsList){
        weaponsProps = weaponsPropsList;
    }

    public List<WeaponProperties> initializeWeaponsDB(){
        weaponsProps = new ArrayList<>();

        // Simple Melee
        weaponsProps.add(new WeaponProperties("Club", 1, "sp", Dice.D4, 1, DamageType.Bludgeoning, 2, "Light"));
        weaponsProps.add(new WeaponProperties("Dagger", 2, "gp", Dice.D4, 1, DamageType.Piercing, 1,
                        "Finesse, light, thrown (range 20/60)"));
        weaponsProps.add(new WeaponProperties("Greatclub", 2, "sp", Dice.D8, 1, DamageType.Bludgeoning, 10,
                        "Two-handed"));
        weaponsProps.add(new WeaponProperties("Handaxe", 5, "gp", Dice.D6, 1, DamageType.Slashing, 2,
                        "Light, thrown (range 20/60)"));
        weaponsProps.add(new WeaponProperties("Javelin", 5, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                        "Thrown (range 30/120)"));
        weaponsProps.add(new WeaponProperties("Light Hammer", 2, "sp", Dice.D4, 1, DamageType.Bludgeoning, 2,
                        "Light, thrown (range 20/60)"));
        weaponsProps.add(new WeaponProperties("Mace", 5, "gp", Dice.D6, 1, DamageType.Bludgeoning, 4, "-"));
        weaponsProps.add(new WeaponProperties("Quarterstaff", 2, "sp", Dice.D6, 1, DamageType.Bludgeoning, 4,
                        "Versatile (1d8)"));
        weaponsProps.add(new WeaponProperties("Sickle", 1, "gp", Dice.D4, 1, DamageType.Slashing, 2, "Light"));
        weaponsProps.add(new WeaponProperties("Spear", 1, "gp", Dice.D6, 1, DamageType.Piercing, 3,
                        "Thrown (range 20/60), versatile (1d8)"));

        // Simple Ranged
        weaponsProps.add(new WeaponProperties("Light Crossbow", 25, "gp", Dice.D8, 1, DamageType.Piercing, 5,
                        "Ammunition (range 80/320), loading, two-handed"));
        weaponsProps.add(new WeaponProperties("Dart", 5, "cp", Dice.D4, 1, DamageType.Piercing, 0.25f,
                        "Finesse, thrown (range 20/60)"));
        weaponsProps.add(new WeaponProperties("Shortbow", 25, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                        "Ammunition (range 80/320), two-handed"));
        weaponsProps.add(new WeaponProperties("Sling", 1, "sp", Dice.D4, 1, DamageType.Bludgeoning, 0,
                        "Ammunition (range 30/120)"));

        // Martial Melee
        weaponsProps.add(new WeaponProperties("Battleaxe", 10, "gp", Dice.D8, 1, DamageType.Slashing, 4,
                        "Versatile (1d10)"));
        weaponsProps.add(new WeaponProperties("Flail", 10, "gp", Dice.D8, 1, DamageType.Bludgeoning, 2, "-"));
        weaponsProps.add(new WeaponProperties("Glaive", 20, "gp", Dice.D10, 1, DamageType.Slashing, 6,
                        "Heavy, reach, two-handed"));
        weaponsProps.add(new WeaponProperties("Greataxe", 30, "gp", Dice.D12, 1, DamageType.Slashing, 7,
                        "Heavy, two-handed"));
        weaponsProps.add(new WeaponProperties("Greatsword", 50, "gp", Dice.D6, 2, DamageType.Slashing, 6,
                        "Heavy, two-handed"));
        weaponsProps.add(new WeaponProperties("Halberd", 20, "gp", Dice.D10, 1, DamageType.Slashing, 6,
                        "Heavy, reach, two-handed"));
        weaponsProps.add(new WeaponProperties("Lance", 10, "gp", Dice.D12, 1, DamageType.Piercing, 6,
                        "Reach, special"));
        weaponsProps.add(new WeaponProperties("Longsword", 15, "gp", Dice.D8, 1, DamageType.Slashing, 3,
                        "Versatile (1d10)"));
        weaponsProps.add(new WeaponProperties("Maul", 10, "gp", Dice.D6, 2, DamageType.Bludgeoning, 10,
                        "Heavy, two-handed"));
        weaponsProps.add(new WeaponProperties("Morningstar", 15, "gp", Dice.D8, 1, DamageType.Piercing, 4, "-"));
        weaponsProps.add(new WeaponProperties("Pike", 5, "gp", Dice.D10, 1, DamageType.Piercing, 18,
                        "Heavy, reach, two-handed"));
        weaponsProps.add(new WeaponProperties("Rapier", 25, "gp", Dice.D8, 1, DamageType.Piercing, 2, "Finesse"));
        weaponsProps.add(new WeaponProperties("Scimitar", 25, "gp", Dice.D6, 1, DamageType.Slashing, 3,
                        "Finesse, light"));
        weaponsProps.add(new WeaponProperties("Shortsword", 10, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                        "Finesse, light"));
        weaponsProps.add(new WeaponProperties("Trident", 5, "gp", Dice.D6, 1, DamageType.Piercing, 4,
                        "Thrown (range 20/60), versatile (1d8)"));
        weaponsProps.add(new WeaponProperties("War Pick", 5, "gp", Dice.D8, 1, DamageType.Piercing, 2, "-"));
        weaponsProps.add(new WeaponProperties("Warhammer", 15, "gp", Dice.D8, 1, DamageType.Bludgeoning, 2,
                        "Versatile (1d10)"));
        weaponsProps.add(new WeaponProperties("Whip", 2, "gp", Dice.D4, 1, DamageType.Slashing, 3,
                        "Finesse, reach"));

        // Martial Ranged
        weaponsProps.add(new WeaponProperties("Blowgun", 10, "gp", Dice.D1, 1, DamageType.Piercing, 1,
                        "Ammunition (range 25/100), loading"));
        weaponsProps.add(new WeaponProperties("One Handed Crossbow", 75, "gp", Dice.D4, 1, DamageType.Piercing, 3,
                        "Ammunition (range 30/120), light, loading"));
        weaponsProps.add(new WeaponProperties("Heavy Crossbow", 50, "gp", Dice.D4, 1, DamageType.Piercing, 18,
                        "Ammunition (range 100/400), heavy, loading, two-handed"));
        weaponsProps.add(new WeaponProperties("Longbow", 50, "gp", Dice.D4, 1, DamageType.Piercing, 2,
                        "Ammunition (range 150/600), heavy, two-handed"));
        weaponsProps.add(new WeaponProperties("Net", 1, "gp", Dice.D4, 1, null, 3, "Special, thrown (range 5/15)"));
        return weaponsProps;
    }
}
