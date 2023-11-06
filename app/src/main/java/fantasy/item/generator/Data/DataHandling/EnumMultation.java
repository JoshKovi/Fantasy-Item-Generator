package fantasy.item.generator.Data.DataHandling;

import java.util.HashMap;
import java.util.List;

import fantasy.item.generator.Data.DataHelpers.Rarity;
import fantasy.item.generator.Data.DataStorage.SqlLiteDBController;
import fantasy.item.generator.Weapon.WeaponProperties;

public class EnumMultation {
    private static HashMap<Rarity, List<String>> rarityMap = genRarityMap();
    private static List<WeaponProperties> weaponProps;

    public static List<String> getRarityDescriptors(Rarity rarity) {
        return rarityMap.get(rarity);
    }

    public static void setRarityMapFromDB(HashMap<Rarity, List<String>> map){
        rarityMap = map;
    }
    public static void setWeaponsProps(List<WeaponProperties> weapons){
        weaponProps = weapons;
    }

    public static List<WeaponProperties> getWeaponsProps(){
        return weaponProps;
    }


    private static HashMap<Rarity, List<String>> genRarityMap() {
        HashMap<Rarity, List<String>> raritys = new HashMap<>();
        for(Rarity eValue : Rarity.values()){
            raritys.put(eValue, eValue.getDefaultDescriptors());
        } 
        return raritys;
    }

    public static void addDescriptor(Rarity rarity, String desc) {
        SqlLiteDBController controller = SqlLiteDBController.getInstance();
        rarityMap.get(rarity).add(desc);
        controller.addListEntryToTable(SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), rarity.name(), desc);

    }

    public static void removeDescriptorByName(Rarity rarity, String desc) {
        SqlLiteDBController controller = SqlLiteDBController.getInstance();
        rarityMap.get(rarity).remove(desc);
        controller.removeListEntryFromTable(SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), rarity.name(), desc);
    }

    public static void removeDescriptorByIndex(Rarity rarity, int index) {
    }

    public static void addDescriptors(Rarity rarity, List<String> descriptors) {
    }

    
}
