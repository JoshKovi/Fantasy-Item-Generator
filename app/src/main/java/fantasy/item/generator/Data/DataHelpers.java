package fantasy.item.generator.Data;

import java.util.Arrays;
import java.util.List;

import fantasy.item.generator.Data.DataHandling.EnumMultation;

public class DataHelpers {
    public static enum DamageType {
        Acid, Bludgeoning, Cold, Fire, Force, Lightning, Necrotic, Piercing,
        Poison, Psychic, Radiant, Slashing, Thunder
    }

    public static enum Dice {
        D1{
            public Dice  up(){return D4;}
            public Dice down(){return D1;}
            public int maxD(){return 1;}
        }, 
        D4{
            public Dice up(){return D6;}
            public Dice down(){return D1;}
            public int maxD(){return 5;}
        }, 
        D6{
            public Dice up(){return D8;}
            public Dice down(){return D4;}
            public int maxD(){return 7;}
        }, 
        D8{
            public Dice up(){return D10;}
            public Dice down(){return D6;}
            public int maxD(){return 9;}
        }, 
        D10{
            public Dice up(){return D12;}
            public Dice down(){return D8;}
            public int maxD(){return 11;}
        }, 
        D12{
            public Dice up(){return D20;}
            public Dice down(){return D10;}
            public int maxD(){return 13;}
        }, 
        D20{
            public Dice up(){return D20;}
            public Dice down(){return D12;}
            public int maxD(){return 21;}
        };
        private static final int LENGTH = 7;
        abstract public Dice up();
        abstract public Dice down();
        abstract public int maxD();
        public int getLength() {return LENGTH;}
    }

    public static enum GPType{
        CP, SP, GP
    }

    public static enum SimpleMeleeWeaponTypes {
        Club, Dagger, Greatclub, Handaxe, Javelin, Light_Hammer, Mace, Quarterstaff,
        Sickle, Spear
    }

    public static enum SimpleRangeWeaponTypes {
        Light_Crossbow, Dart, Shortbow, Sling
    }

    public static enum MartialMeleeWeaponTypes {
        Battleaxe, Flail, Glaive, Greataxe, Greatsword, Halberd, Lance, Longsword, Maul, Morningstar,
        Pike, Rapier, Scimitar, Shortsword, Trident, War_Pick, Warhammer, Whip
    }

    public static enum MartialRangeWeaponTypes {
        Blowgun, One_Handed_Crossbow, Heavy_Crossbow, Longbow, Net;
    }

    public static enum WeaponType {
        SimpleMeleeWeaponType{
            public Enum<SimpleMeleeWeaponTypes>[] getSubTypes(){return SimpleMeleeWeaponTypes.values();}
            public int getSubLength(){return SimpleMeleeWeaponTypes.values().length;}
        }, 
        SimpleRangeWeaponType{
            public Enum<SimpleRangeWeaponTypes>[] getSubTypes() {return SimpleRangeWeaponTypes.values();}
            public int getSubLength(){return SimpleRangeWeaponTypes.values().length;}
        }, 
        MartialMeleeWeaponType{
            public Enum<MartialMeleeWeaponTypes>[] getSubTypes(){return MartialMeleeWeaponTypes.values();}
            public int getSubLength(){return MartialMeleeWeaponTypes.values().length;}
        }, 
        MartialRangeWeaponType{
            public Enum<MartialRangeWeaponTypes>[] getSubTypes(){return MartialRangeWeaponTypes.values();}
            public int getSubLength(){return MartialRangeWeaponTypes.values().length;}
        };

        private static final int LENGTH = 4;
        public static int getLength() {return LENGTH;}
        public abstract Enum<?>[] getSubTypes();
        public abstract int getSubLength();

        public static WeaponType reverseTypeLookup(String subWeaponType){
            for(WeaponType weaponType : WeaponType.values()){
                for(Enum<?> eValue : weaponType.getSubTypes()){
                    if(eValue.name().equalsIgnoreCase(subWeaponType)) return weaponType;
                }
            }
            return null;
        }
        
    }

    public static enum Rarity {
        Junk{
            public Rarity up(){return Common;}
            public Rarity down(){return Junk;}
            public int getIntValue(){return 0;};
            public List<String> getDefaultDescriptors(){
                return Arrays.asList(new String[]{
                    "Trash", "Broken", "Worthless", "Junk", 
                    "Dingy", "Rusty", "Destroyed", "Scrap"
                });
            }
        }, 
        Common{
            public Rarity up(){return Uncommon;}
            public Rarity down(){return Junk;}
            public int getIntValue(){return 20;};
            public List<String> getDefaultDescriptors(){
                return Arrays.asList(new String[]{
                    "Useful", "Dull", "Regular", "Common",
                    "Normal", "Unexceptional", "Not Junk", "Functional"
                });
            }
        }, 
        Uncommon{
            public Rarity up(){return Rare;}
            public Rarity down(){return Common;}
            public int getIntValue(){return 45;};
            public List<String> getDefaultDescriptors(){
                return Arrays.asList(new String[]{
                    "Uncommon", "Unusual", "Exceptional", "Shiny",
                    "Pointy", "The Bee Knees", "Nice", "Valuable",
                    "Sharp", "Fine"
                });
            }
        }, 
        Rare{
            public Rarity up(){return Legendary;}
            public Rarity down(){return Uncommon;}
            public int getIntValue(){return 75;};
            public List<String> getDefaultDescriptors(){
                return Arrays.asList(new String[]{
                    "Rare", "Mastercrafted", "Unnaturally Sharp", "Magic Infused",
                    "Well Crafted", "Remarkable", "Fuck Yeah this is Rare", "Resonating",
                    "Magical", "Unyeilding"
                });
            }
        }, 
        Legendary{
            public Rarity up(){return Legendary;}
            public Rarity down(){return Rare;}
            public int getIntValue(){return 100;};
            public List<String> getDefaultDescriptors(){
                return Arrays.asList(new String[]{
                    "Legendary", "One of A Kind", "Dominating", "GodKiller",
                    "Outstanding", "Mythical", "Heroic", "Fabled", "Lionized",
                    "Renowned", "Venerable", "Exalted", "Glorious", "Immortal",
                    "Famous"
                });
            }
        };

        private static final int LENGTH = 5; //Update for changes.
        public abstract Rarity up();
        public abstract Rarity down();
        public abstract int getIntValue();
        public abstract List<String> getDefaultDescriptors();
        public static int getLength() {return LENGTH;}
        public List<String> getDescriptors(Rarity rarity){
            return EnumMultation.getRarityDescriptors(rarity);
        }
        public void addDescriptor(Rarity rarity, String desc){
            EnumMultation.addDescriptor(rarity, desc);
        }
        public void addDescriptors(Rarity rarity, List<String> descriptors){
            EnumMultation.addDescriptors(rarity, descriptors);
        }
        public void removeDescriptorByName(Rarity rarity, String desc){
            EnumMultation.removeDescriptorByName(rarity, desc);
        }
        public void removeDescriptorByIndex(Rarity rarity, int index){
            EnumMultation.removeDescriptorByIndex(rarity, index);
        }
    }
}
