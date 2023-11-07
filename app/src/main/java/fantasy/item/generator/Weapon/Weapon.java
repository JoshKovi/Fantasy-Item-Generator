package fantasy.item.generator.Weapon;

import java.util.Random;

import fantasy.item.generator.Data.Item;
import fantasy.item.generator.Data.DataHelpers.DamageType;
import fantasy.item.generator.Data.DataHelpers.Dice;
import fantasy.item.generator.Data.DataHelpers.GPType;
import fantasy.item.generator.Data.DataHelpers.Rarity;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import javax.persistence.Entity;

@Entity
public class Weapon extends Item {

    private WeaponType weaponType;
    private String weaponSubType;
    
    private double variance;
    private double averageDmgPerTurn10K;
    private String info;

    private Dice baseDie;
    private int baseMulti;

    private Dice mainDie;
    private DamageType mainDamageType;
    private int mainMultiple;

    private Dice additionalDie;
    private DamageType addDamageType;
    private int addMultiple;

    public Weapon(){
    }
    public Weapon(Rarity rarity){
        this(rarity, null);
    }
    public Weapon(Integer roll){
        this(Rarity.values()[getRandomInt(Rarity.getLength())], roll);
    }
    public Weapon(Rarity rarity, Integer roll){
        this(rarity, roll, WeaponType.values()[getRandomInt(WeaponType.getLength())]);
    }
    public Weapon(Rarity rarity, Integer roll, WeaponType weaponType){
        this(rarity, roll, weaponType, weaponType.getSubTypes()[getRandomInt(weaponType.getSubLength())], null);

    }
    public Weapon(Rarity rarity, Integer roll, Enum<?> weaponSubType){
        this(rarity, roll, WeaponType.reverseTypeLookup(weaponSubType.name()), weaponSubType, null);

    }
    public Weapon(Rarity rarity, Integer roll, WeaponType weaponType, Enum<?> weaponSubType, DamageType addDamageType){
        if(rarity == null){
            rarity = Rarity.values()[getRandomInt(Rarity.getLength())];
        }
        this.setRarity(rarity);
        if(roll == null){
            roll = -100;
        }
        this.setRoll(roll);
        if (weaponType == null && weaponSubType == null){
            weaponType = WeaponType.values()[getRandomInt(WeaponType.getLength())];
            weaponSubType = weaponType.getSubTypes()[getRandomInt(weaponType.getSubLength())];
        } else if (weaponType == null) {
            weaponType = WeaponType.reverseTypeLookup(weaponSubType.name());
        } else if (weaponSubType == null){
            weaponSubType = weaponType.getSubTypes()[getRandomInt(weaponType.getSubLength())];
        }
        this.weaponType = weaponType;
        this.weaponSubType = weaponSubType.name().replace("_", " ");
        updateFromWeapon(WeaponsData.getInstance().getWeaponByName(this.weaponSubType));
        if(addDamageType == null){
            addDamageType = DamageType.values()[getRandomInt(DamageType.values().length)];
        }
        this.addDamageType = addDamageType;
        generateDieFromRarity();
        getDamageAveragePer10k();
        generateName();
    }

    public void generateDieFromRarity() {
        Rarity rarity = this.getRarity();
        Integer rollLocal = (getRoll() == null) ? -100: getRoll();
        boolean rolled = (rollLocal != -100);
        double rollMod = 1;

        if(rollLocal >= 20){
            rollMod = 1.5;
        } else if (rolled && rollLocal <= 1){
            rollMod = 1.5;
        } 
        //If roll is above average add it to variance, if less subtract it. (Divided in half so as to not be overwhelming)
        rollMod *= (rolled) ? (rollLocal/10.0f - 1.0f)/2.0f : 0.0f; 

        int basePercent = rarity.getIntValue();
        double bottomRange = basePercent * 0.75f * 0.01;
        double highEnd = basePercent * 2.0f * 0.01 + 0.01;
        double variance = (new Random().nextDouble(bottomRange, highEnd)) + (basePercent * 0.01) + rollMod;
        Dice up = this.baseDie.up();
        Dice down = this.baseDie.down();
        int addDie =  (up.compareTo(this.baseDie) == 0) ? 1 : 0;

        // Variance is used to determine bonus on base weapon.
        if(Double.compare(variance, 1.80f) >= 0){
            setFromRarity(up, this.baseMulti + 2 + addDie, this.baseDie, 2, rarity);
        } else if(Double.compare(variance, 1.40f) >= 0){
            setFromRarity(up, this.baseMulti + 1 + addDie, down, 3, rarity);
        } else if(Double.compare(variance, 1.20f) >= 0){
            setFromRarity(up, this.baseMulti + 1 + addDie, down, 2, rarity);
        } else if(Double.compare(variance, 1.00f) >= 0){
            setFromRarity(this.baseDie, this.baseMulti, down, 1, rarity);
        } else if(Double.compare(variance, 0.80f) >= 0){
            setFromRarity(this.baseDie, this.baseMulti, down, 1, rarity);
        } else if(Double.compare(variance, 0.60f) >= 0){
            setFromRarity(this.baseDie, this.baseMulti, down.down(), 1, rarity);
        } else if(Double.compare(variance, 0.25f) >= 0){
            setFromRarity(this.baseDie, this.baseMulti, Dice.D1, 0, rarity);
        } else {
            setFromRarity(down, 1, Dice.D1, 0, rarity);
        }
        this.variance = variance;
        setCost((int)(getCost() * (Rarity.Junk.compareTo(rarity) * -4 * this.addMultiple + 1)));
    }

    private void setFromRarity(Dice main, int mainMulti, Dice addDice, int addMulti, Rarity rarity){
        mainMulti = (mainMulti > 0) ? mainMulti :  (rarity.compareTo(Rarity.Junk) >= 1) ? 1 : mainMulti;
        this.mainDie = main;
        this.mainMultiple = mainMulti;
        this.additionalDie = addDice;
        this.addMultiple = addMulti;
    }


    protected void updateFromWeapon(WeaponProperties weaponProps) {
        this.mainDamageType = weaponProps.getDamageType();
        this.baseDie = weaponProps.getDie();
        this.baseMulti = (weaponProps.getDiceMulti() <= 0) ? 1 : weaponProps.getDiceMulti();
        this.info = weaponProps.getInfo();
        setGPUnit(GPType.valueOf(weaponProps.getCostUnit()));
        setCost(weaponProps.getCost());
        setWeight(weaponProps.getWeight());
        setWeightUnits("lbs");
        

    }

    public String getDamageAveragePer10k(){
        double average = 0;
        for(int i = 0; i < 10000; i ++){
            int total = 0;
            for(int j = 0; j < this.mainMultiple; j++){
                if(this.getMainDie().maxD() == 1){
                    total += 1;
                }
                else{
                    total += new Random().nextInt(1, this.getMainDie().maxD());
                }
                
            }
            for(int j = 0; j < this.addMultiple; j++){
                if(this.getAdditionalDie().maxD() == 1){
                    total += 1;
                }
                else{
                    total += new Random().nextInt(1, this.getAdditionalDie().maxD());
                }
            }
            average += total;
        }
        this.averageDmgPerTurn10K = (double)average/10000.0f;
        return "" +((double)average)/10000.0f;
    }

    public void generateName() {
        String addDmgType = "";

        if(this.getAdditionalMutliple() > 0){
            addDmgType = this.getAdditionalDamageType().name() + " ";
        }
        Rarity rarity = this.getRarity();
        int picker = new Random().nextInt(rarity.getDescriptors(rarity).size());
        String name = rarity.getDescriptors(rarity).get(picker) + " " + addDmgType
            + this.weaponSubType;
        this.setName(name);
    }

    // Setters
    public void setWeaponSubType(String weaponSubType){
        this.weaponSubType = weaponSubType;
    }

    public void setAverageDmgPerTurn10K(double avg){
        this.averageDmgPerTurn10K = avg;
    }

    public void setBaseDie(Dice die) {
        baseDie = die;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setMainDie(Dice mainDie) {
        this.mainDie = mainDie;
    }

    public void setMainDamageType(DamageType mainDamageType) {
        this.mainDamageType = mainDamageType;
    }

    public void setMainMultiple(int mainMultiple) {
        this.mainMultiple = mainMultiple;
    }

    public void setAdditionalDie(Dice additionalDie) {
        this.additionalDie = additionalDie;
    }

    public void setAddDamageType(DamageType addDamageType) {
        this.addDamageType = addDamageType;
    }

    public void setAddMultiple(int addMultiple) {
        this.addMultiple = addMultiple;
    }

    // Getters
    public double getVariance(){
        return variance;
    }
    public String getInfo() {
        return info;
    }

    public String getWeaponSubType(){
        return weaponSubType;
    }

    public double setAverageDmgPerTurn10K(){
        return this.averageDmgPerTurn10K;
    }

    public String getWeaponTypeString() {
        return (weaponType == null) ? null:weaponType.name().replaceAll("_", " ");
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public String getMainDamageString() {
        return (mainDamageType == null) ? null: mainDamageType.name();
    }

    public DamageType getMainDamageType() {
        return mainDamageType;
    }

    public String getMainDieString() {
        return (mainDie == null) ? null: mainDie.name();
    }

    public Dice getMainDie() {
        return mainDie;
    }

    public int getMainMutliple() {
        return mainMultiple;
    }

    public String getAdditionalDieString() {
        return (additionalDie == null) ? null: additionalDie.name();
    }

    public Dice getAdditionalDie() {
        return additionalDie;
    }

    public String getAdditionalDamageString() {
        return (addDamageType == null) ? null: addDamageType.name();
    }

    public DamageType getAdditionalDamageType() {
        return addDamageType;
    }

    public int getAdditionalMutliple() {
        return addMultiple;
    }

}
