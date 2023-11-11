package fantasy.item.generator.Data.Attributes;

import fantasy.item.generator.Data.Attributes.DataHelpers.*;

public enum WeaponType {
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
