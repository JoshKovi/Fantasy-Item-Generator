package fantasy.item.generator.Data.DataHandling;

import java.util.HashMap;
import java.util.List;

import fantasy.item.generator.Data.Attributes.Rarity;
import fantasy.item.generator.Data.DataStorage.SqlLiteDBController;

public class RarityMutilation {
    private static HashMap<Rarity, List<String>> rarityMap = genRarityMap();


    public static List<String> getRarityDescriptors(Rarity rarity) {
        return rarityMap.get(rarity);
    }

    public static void setRarityMapFromDB(HashMap<Rarity, List<String>> map){
        rarityMap = map;
    }


    private static HashMap<Rarity, List<String>> genRarityMap() {
        HashMap<Rarity, List<String>> raritys = new HashMap<>();
        for(Rarity eValue : Rarity.values()){
            raritys.put(eValue, eValue.getDefaultDescriptors());
        } 
        return raritys;
    }

    public static void addDescriptor(Rarity rarity, String desc) {
        rarityMap.get(rarity).add(desc);
        SqlLiteDBController.getInstance().addListEntryToTable(
            SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), rarity.name(), desc);

    }

    public static void removeDescriptorByName(Rarity rarity, String desc) {
        rarityMap.get(rarity).remove(desc);
        SqlLiteDBController.getInstance().removeListEntryFromTable(
            SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), rarity.name(), desc);
    }

    public static void removeDescriptorByIndex(Rarity rarity, int index) {
        String desc = rarityMap.get(rarity).remove(index);
        if(desc != null && !desc.isEmpty()){
            SqlLiteDBController.getInstance().removeListEntryFromTable(
                SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), 
                rarity.name(), desc);
        } else {
            System.out.println("The Descriptor did not exist.");
        }
        
    }

    public static void addDescriptors(Rarity rarity, List<String> descriptors) {
        rarityMap.get(rarity).addAll(descriptors);
        for(String desc : descriptors){
            SqlLiteDBController.getInstance().addListEntryToTable(
                SqlLiteDBController.RARITY_DESCRIPTORS_TABLE_NAME + rarity.name(), 
                rarity.name(), desc);
        }
    }
}
