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
            public int maxD(){return 4;}
        }, 
        D6{
            public Dice up(){return D8;}
            public Dice down(){return D4;}
            public int maxD(){return 6;}
        }, 
        D8{
            public Dice up(){return D10;}
            public Dice down(){return D6;}
            public int maxD(){return 8;}
        }, 
        D10{
            public Dice up(){return D12;}
            public Dice down(){return D8;}
            public int maxD(){return 10;}
        }, 
        D12{
            public Dice up(){return D20;}
            public Dice down(){return D10;}
            public int maxD(){return 12;}
        }, 
        D20{
            public Dice up(){return D20;}
            public Dice down(){return D12;}
            public int maxD(){return 20;}
        };

        abstract public Dice up();
        abstract public Dice down();
        abstract public int maxD();
    }

    public static enum GPType{
        CP, SP, GP
    }

    public static enum SimpleMeleeWeaponType {
        Club, Dagger, Greatclub, Handaxe, Javelin, Light_Hammer, Mace, Quarterstaff,
        Sickle, Spear
    }

    public static enum SimpleRangeWeaponType {
        Light_Crossbow, Dart, Shortbow, Sling
    }

    public static enum MartialMeleeWeaponType {
        Battleaxe, Flail, Glaive, Greataxe, Greatsword, Halberd, Lance, Longsword, Maul, Morningstar,
        Pike, Rapier, Scimitar, Shortsword, Trident, War_Pick, Warhammer, Whip
    }

    public static enum MartialRangeWeaponType {
        Blowgun, One_Handed_Crossbow, Heavy_Crossbow, Longbow, Net
    }

    public static enum WeaponType {
        SimpleMeleeWeaponType, SimpleRangeWeaponType, MartialMeleeWeaponType, MartialRangeWeaponType
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

        public abstract Rarity up();
        public abstract Rarity down();
        public abstract int getIntValue();
        public abstract List<String> getDefaultDescriptors();
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
