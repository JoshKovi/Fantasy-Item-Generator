package fantasy.item.generator.Weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fantasy.item.generator.Data.DataHelpers.*;

public class WeaponGenerator {
    private WeaponsData weaponsData;

    /**
     * Generates a completely random weapon
     */
    public WeaponGenerator() {
        weaponsData = new WeaponsData();
    }

    public AbstractWeapon generateWeapon() {
        int level = new Random().nextInt(20);
        int picker = new Random().nextInt(Rarity.values().length);
        Rarity rarity = Rarity.values()[picker];
        return generateWeapon(rarity, level);
    }

    public AbstractWeapon generateWeapon(Integer level) {
        int picker = new Random().nextInt(Rarity.values().length);
        Rarity rarity = Rarity.values()[picker];
        return generateWeapon(rarity, level);
    }

    public AbstractWeapon generateWeapon(Rarity rarity) {
        Integer level = new Random().nextInt(20);
        return generateWeapon(rarity, level);
    }

    public AbstractWeapon generateWeapon(Rarity rarity, Integer level) {
        int picker = new Random().nextInt(WeaponType.values().length);
        WeaponType weaponType = WeaponType.values()[picker];

        return generateWeapon(rarity, level, weaponType);
    }

    public AbstractWeapon generateWeapon(Rarity rarity, Integer level, WeaponType weaponType) {
        int picker;
        if(rarity == null){
            picker = new Random().nextInt(Rarity.values().length);
            rarity = Rarity.values()[picker];
        }

        if (level == null){
            level = new Random().nextInt(20);
        }
        

        AbstractWeapon weapon;

        switch (weaponType) {
            case SimpleMeleeWeaponType:
                SimpleMeleeWeapon simpleMWeapon = new SimpleMeleeWeapon();
                picker = new Random().nextInt(SimpleMeleeWeaponType.values().length);
                simpleMWeapon.setMinorWeaponType(SimpleMeleeWeaponType.values()[picker]);
                weapon = simpleMWeapon;
                break;
            case SimpleRangeWeaponType:
                SimpleRangeWeapon simpleRWeapon = new SimpleRangeWeapon();
                picker = new Random().nextInt(SimpleRangeWeaponType.values().length);
                simpleRWeapon.setMinorWeaponType(SimpleRangeWeaponType.values()[picker]);
                weapon = simpleRWeapon;
                break;
            case MartialMeleeWeaponType:
                MartialMeleeWeapon MartialMWeapon = new MartialMeleeWeapon();
                picker = new Random().nextInt(MartialMeleeWeaponType.values().length);
                MartialMWeapon.setMinorWeaponType(MartialMeleeWeaponType.values()[picker]);
                weapon = MartialMWeapon;
                break;
            case MartialRangeWeaponType:
                MartialRangeWeapon MartialRWeapon = new MartialRangeWeapon();
                picker = new Random().nextInt(MartialRangeWeaponType.values().length);
                MartialRWeapon.setMinorWeaponType(MartialRangeWeaponType.values()[picker]);
                weapon = MartialRWeapon;
                break;
            default:
                SimpleMeleeWeapon defaultClub = new SimpleMeleeWeapon();
                defaultClub.setMinorWeaponType(SimpleMeleeWeaponType.Club);
                weapon = defaultClub;
                break;
        }
        WeaponProperties weaponProps =  weaponsData.getWeaponByName(weapon.getMinorWeaponType());
        if (weaponProps == null){
            System.out.println(weapon.getMinorWeaponType());
        }
        weapon.updateFromWeapon(weaponProps);
        weapon.generateDieFromRarity(rarity);
        generateNameFromWeapon(weapon);
        weapon.getDamageAveragePer1k();
        return weapon;
    }

    public AbstractWeapon generateWeapon(Rarity rarity, Integer level, String minorWeaponType, String additionalDmgTypeString) {
        int picker;
        if(rarity == null){
            picker = new Random().nextInt(Rarity.values().length);
            rarity = Rarity.values()[picker];
        }
        if (level == null){
            level = new Random().nextInt(20);
        }
        if (additionalDmgTypeString == null){
            picker = new Random().nextInt(DamageType.values().length);
            additionalDmgTypeString = DamageType.values()[picker].name();
        }
        DamageType additionalDamageType = DamageType.valueOf(additionalDmgTypeString);
        WeaponType weaponType;

        

        AbstractWeapon weapon;
        if(enumContains(SimpleMeleeWeaponType.values(), minorWeaponType)){
            weapon = new SimpleMeleeWeapon(SimpleMeleeWeaponType.valueOf(minorWeaponType));
            weaponType = WeaponType.SimpleMeleeWeaponType;
        } else if(enumContains(SimpleRangeWeaponType.values(), minorWeaponType)){
            weapon = new SimpleRangeWeapon(SimpleRangeWeaponType.valueOf(minorWeaponType));
            weaponType = WeaponType.SimpleRangeWeaponType;
        } else if(enumContains(MartialMeleeWeaponType.values(), minorWeaponType)){
            weapon = new MartialMeleeWeapon(MartialMeleeWeaponType.valueOf(minorWeaponType));
            weaponType = WeaponType.MartialMeleeWeaponType;
        } else if(enumContains(MartialRangeWeaponType.values(), minorWeaponType)){
            weapon = new MartialRangeWeapon(MartialRangeWeaponType.valueOf(minorWeaponType));
            weaponType = WeaponType.MartialRangeWeaponType;
        } else {
            throw new IllegalArgumentException("This weapon is not recognized!");
        }
        weapon.setWeaponType(weaponType);
        weapon.setAddDamageType(additionalDamageType);


        WeaponProperties weaponProps =  weaponsData.getWeaponByName(weapon.getMinorWeaponType());
        if (weaponProps == null){
            System.out.println(weapon.getMinorWeaponType());
        }
        weapon.updateFromWeapon(weaponProps);
        weapon.generateDieFromRarity(rarity);
        weapon.setAddDamageType(additionalDamageType);
        generateNameFromWeapon(weapon);
        weapon.getDamageAveragePer1k();
        return weapon;
    }

    private void generateNameFromWeapon(AbstractWeapon weapon) {
        String addDmgType = "";

        if(weapon.getAdditionalMutliple() > 0){
            addDmgType = weapon.getAdditionalDamageType().name() + " ";
        }
        Rarity rarity = weapon.getRarity();
        int picker = new Random().nextInt(rarity.getDescriptors(rarity).size());
        String name = weapon.getRarity().getDescriptors(rarity).get(picker) + " " + addDmgType
            + weapon.getMinorWeaponType();
        weapon.setName(name);
    }

    public static boolean enumContains(Enum<?>[] enumToCheck, String value){
        for(Enum<?> eValue : enumToCheck){
            if(eValue.name().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }

    public WeaponsData getWeaponsData(){
        return weaponsData;
    }

    public void setWeaponsList(List<WeaponProperties> data){
        weaponsData.setWeaponsList(data);
    }
}
