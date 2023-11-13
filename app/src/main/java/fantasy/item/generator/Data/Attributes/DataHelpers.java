package fantasy.item.generator.Data.Attributes;

import java.util.ArrayList;
import java.util.List;

public class DataHelpers {

    public static enum DamageType {
        Acid, Bludgeoning, Cold, Fire, Force, Lightning, Necrotic, Piercing,
        Poison, Psychic, Radiant, Slashing, Thunder
    }

    public static enum GPType{
        CP, SP, GP
    }

    public static enum SimpleMeleeWeaponTypes {
        Club, Dagger, Greatclub, Handaxe, Javelin, Light_Hammer, Mace, Quarterstaff,
        Sickle, Spear;

        public List<String> additionalTypes;
        public void addItem(String additionalType){
            if(this.additionalTypes == null) this.additionalTypes = new ArrayList<>();
            this.additionalTypes.add(additionalType);
        }
        public void addItems(List<String> allItems){
            this.additionalTypes = allItems;
        }
    }

    public static enum SimpleRangeWeaponTypes {
        Light_Crossbow, Dart, Shortbow, Sling;

        public List<String> additionalTypes;
        public void addItem(String additionalType){
            if(this.additionalTypes == null) this.additionalTypes = new ArrayList<>();
            this.additionalTypes.add(additionalType);
        }
        public void addItems(List<String> allItems){
            this.additionalTypes = allItems;
        }
    }

    public static enum MartialMeleeWeaponTypes {
        Battleaxe, Flail, Glaive, Greataxe, Greatsword, Halberd, Lance, Longsword, Maul, Morningstar,
        Pike, Rapier, Scimitar, Shortsword, Trident, War_Pick, Warhammer, Whip;

        public List<String> additionalTypes;
        public void addItem(String additionalType){
            if(this.additionalTypes == null) this.additionalTypes = new ArrayList<>();
            this.additionalTypes.add(additionalType);
        }
        public void addItems(List<String> allItems){
            this.additionalTypes = allItems;
        }
    }

    public static enum MartialRangeWeaponTypes {
        Blowgun, One_Handed_Crossbow, Heavy_Crossbow, Longbow, Net;

        public List<String> additionalTypes;
        public void addItem(String additionalType){
            if(this.additionalTypes == null) this.additionalTypes = new ArrayList<>();
            this.additionalTypes.add(additionalType);
        }
        public void addItems(List<String> allItems){
            this.additionalTypes = allItems;
        }
    }
}
