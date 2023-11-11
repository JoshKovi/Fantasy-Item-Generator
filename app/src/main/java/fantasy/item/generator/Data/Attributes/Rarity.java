package fantasy.item.generator.Data.Attributes;

import java.util.Arrays;
import java.util.List;

import fantasy.item.generator.Data.DataHandling.RarityMutilation;

public enum Rarity {
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
        return RarityMutilation.getRarityDescriptors(rarity);
    }
    public void addDescriptor(Rarity rarity, String desc){
        RarityMutilation.addDescriptor(rarity, desc);
    }
    public void addDescriptors(Rarity rarity, List<String> descriptors){
        RarityMutilation.addDescriptors(rarity, descriptors);
    }
    public void removeDescriptorByName(Rarity rarity, String desc){
        RarityMutilation.removeDescriptorByName(rarity, desc);
    }
    public void removeDescriptorByIndex(Rarity rarity, int index){
        RarityMutilation.removeDescriptorByIndex(rarity, index);
    }
}
