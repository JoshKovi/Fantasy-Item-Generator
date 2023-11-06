package fantasy.item.generator.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fantasy.item.generator.Data.DataHandling.EnumMultation;
import fantasy.item.generator.Data.DataHelpers.DamageType;
import fantasy.item.generator.Data.DataHelpers.Dice;
import fantasy.item.generator.Data.DataStorage.SqlLiteDBController;

/**
 * TODO: Replace this with some DB backing and or just a json file or some
 * underlining mutable data source.
 * /* Honestly, when this is done the entire Base level information should be
 * contained for simplier building stuff.
 */

public class WeaponsData {
        public static List<WeaponProperties> weapons;

        public WeaponsData() {
                SqlLiteDBController.getInstance();
                weapons = EnumMultation.getWeaponsProps();
        }

        public static List<WeaponProperties> initializeWeaponsDB(){
                weapons = new ArrayList<>();
                // Simple Melee
                weapons.add(new WeaponProperties("Club", 1, "sp", Dice.D4, 1, DamageType.Bludgeoning, 2, "Light"));
                weapons.add(new WeaponProperties("Dagger", 2, "gp", Dice.D4, 1, DamageType.Piercing, 1,
                                "Finesse, light, thrown (range 20/60)"));
                weapons.add(new WeaponProperties("Greatclub", 2, "sp", Dice.D8, 1, DamageType.Bludgeoning, 10,
                                "Two-handed"));
                weapons.add(new WeaponProperties("Handaxe", 5, "gp", Dice.D6, 1, DamageType.Slashing, 2,
                                "Light, thrown (range 20/60)"));
                weapons.add(new WeaponProperties("Javelin", 5, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                                "Thrown (range 30/120)"));
                weapons.add(new WeaponProperties("Light Hammer", 2, "sp", Dice.D4, 1, DamageType.Bludgeoning, 2,
                                "Light, thrown (range 20/60)"));
                weapons.add(new WeaponProperties("Mace", 5, "gp", Dice.D6, 1, DamageType.Bludgeoning, 4, "-"));
                weapons.add(new WeaponProperties("Quarterstaff", 2, "sp", Dice.D6, 1, DamageType.Bludgeoning, 4,
                                "Versatile (1d8)"));
                weapons.add(new WeaponProperties("Sickle", 1, "gp", Dice.D4, 1, DamageType.Slashing, 2, "Light"));
                weapons.add(new WeaponProperties("Spear", 1, "gp", Dice.D6, 1, DamageType.Piercing, 3,
                                "Thrown (range 20/60), versatile (1d8)"));

                // Simple Ranged
                weapons.add(new WeaponProperties("Light Crossbow", 25, "gp", Dice.D8, 1, DamageType.Piercing, 5,
                                "Ammunition (range 80/320), loading, two-handed"));
                weapons.add(new WeaponProperties("Dart", 5, "cp", Dice.D4, 1, DamageType.Piercing, 0.25f,
                                "Finesse, thrown (range 20/60)"));
                weapons.add(new WeaponProperties("Shortbow", 25, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                                "Ammunition (range 80/320), two-handed"));
                weapons.add(new WeaponProperties("Sling", 1, "sp", Dice.D4, 1, DamageType.Bludgeoning, 0,
                                "Ammunition (range 30/120)"));

                // Martial Melee
                weapons.add(new WeaponProperties("Battleaxe", 10, "gp", Dice.D8, 1, DamageType.Slashing, 4,
                                "Versatile (1d10)"));
                weapons.add(new WeaponProperties("Flail", 10, "gp", Dice.D8, 1, DamageType.Bludgeoning, 2, "-"));
                weapons.add(new WeaponProperties("Glaive", 20, "gp", Dice.D10, 1, DamageType.Slashing, 6,
                                "Heavy, reach, two-handed"));
                weapons.add(new WeaponProperties("Greataxe", 30, "gp", Dice.D12, 1, DamageType.Slashing, 7,
                                "Heavy, two-handed"));
                weapons.add(new WeaponProperties("Greatsword", 50, "gp", Dice.D6, 2, DamageType.Slashing, 6,
                                "Heavy, two-handed"));
                weapons.add(new WeaponProperties("Halberd", 20, "gp", Dice.D10, 1, DamageType.Slashing, 6,
                                "Heavy, reach, two-handed"));
                weapons.add(new WeaponProperties("Lance", 10, "gp", Dice.D12, 1, DamageType.Piercing, 6,
                                "Reach, special"));
                weapons.add(new WeaponProperties("Longsword", 15, "gp", Dice.D8, 1, DamageType.Slashing, 3,
                                "Versatile (1d10)"));
                weapons.add(new WeaponProperties("Maul", 10, "gp", Dice.D6, 2, DamageType.Bludgeoning, 10,
                                "Heavy, two-handed"));
                weapons.add(new WeaponProperties("Morningstar", 15, "gp", Dice.D8, 1, DamageType.Piercing, 4, "-"));
                weapons.add(new WeaponProperties("Pike", 5, "gp", Dice.D10, 1, DamageType.Piercing, 18,
                                "Heavy, reach, two-handed"));
                weapons.add(new WeaponProperties("Rapier", 25, "gp", Dice.D8, 1, DamageType.Piercing, 2, "Finesse"));
                weapons.add(new WeaponProperties("Scimitar", 25, "gp", Dice.D6, 1, DamageType.Slashing, 3,
                                "Finesse, light"));
                weapons.add(new WeaponProperties("Shortsword", 10, "gp", Dice.D6, 1, DamageType.Piercing, 2,
                                "Finesse, light"));
                weapons.add(new WeaponProperties("Trident", 5, "gp", Dice.D6, 1, DamageType.Piercing, 4,
                                "Thrown (range 20/60), versatile (1d8)"));
                weapons.add(new WeaponProperties("War Pick", 5, "gp", Dice.D8, 1, DamageType.Piercing, 2, "-"));
                weapons.add(new WeaponProperties("Warhammer", 15, "gp", Dice.D8, 1, DamageType.Bludgeoning, 2,
                                "Versatile (1d10)"));
                weapons.add(new WeaponProperties("Whip", 2, "gp", Dice.D4, 1, DamageType.Slashing, 3,
                                "Finesse, reach"));

                // Martial Ranged
                weapons.add(new WeaponProperties("Blowgun", 10, "gp", Dice.D1, 1, DamageType.Piercing, 1,
                                "Ammunition (range 25/100), loading"));
                weapons.add(new WeaponProperties("One Handed Crossbow", 75, "gp", Dice.D4, 1, DamageType.Piercing, 3,
                                "Ammunition (range 30/120), light, loading"));
                weapons.add(new WeaponProperties("Heavy Crossbow", 50, "gp", Dice.D4, 1, DamageType.Piercing, 18,
                                "Ammunition (range 100/400), heavy, loading, two-handed"));
                weapons.add(new WeaponProperties("Longbow", 50, "gp", Dice.D4, 1, DamageType.Piercing, 2,
                                "Ammunition (range 150/600), heavy, two-handed"));
                weapons.add(new WeaponProperties("Net", 1, "gp", Dice.D4, 1, null, 3, "Special, thrown (range 5/15)"));

                return weapons;
        }

        public List<WeaponProperties> getWeaponProperties() {
                return weapons;
        }

        public WeaponProperties getWeaponByIndex(int i){
                return weapons.get(i);
        }

        public WeaponProperties getWeaponByName(String name) {
                return weapons.stream()
                                .filter(weapon -> weapon.getWeaponType().equalsIgnoreCase(name))
                                .findFirst()
                                .orElse(null);
        }

        public void setWeaponsList(List<WeaponProperties> weaponsList){
                weapons = weaponsList;
        }

}
