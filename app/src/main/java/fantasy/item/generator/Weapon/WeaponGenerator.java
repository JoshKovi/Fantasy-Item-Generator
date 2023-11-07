package fantasy.item.generator.Weapon;


import java.util.List;


public class WeaponGenerator {
    private WeaponsData weaponsData;

    /**
     * Generates a completely random weapon
     */
    public WeaponGenerator() {
        weaponsData = WeaponsData.getInstance();
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
        WeaponsData.getInstance().setWeaponsList(data);
    }
}
