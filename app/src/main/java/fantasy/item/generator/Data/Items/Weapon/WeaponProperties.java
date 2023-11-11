package fantasy.item.generator.Data.Items.Weapon;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fantasy.item.generator.Data.Attributes.Dice;
import fantasy.item.generator.Data.Attributes.DataHelpers.DamageType;

@Entity
public class WeaponProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String weaponType;
    private int cost;
    private String costUnit;
    private Dice die;
    private int diceMulti;
    private DamageType damageType;
    private double weight;
    private String info;
    private String[] profiles;

    public WeaponProperties(){};

    public WeaponProperties(String weaponType, int cost, String costUnit,
            Dice die, int diceMulti, DamageType damageType, double weight, String info) {
                this(weaponType, cost, costUnit, die, diceMulti, damageType, weight, info, new String[]{"Defaults"});
    }

    public WeaponProperties(String weaponType, int cost, String costUnit,
            Dice die, int diceMulti, DamageType damageType, double weight, String info, String[] profiles) {
        this.weaponType = weaponType;
        this.cost = cost;
        this.costUnit = costUnit.toUpperCase();
        this.die = die;
        this.diceMulti = diceMulti;
        this.damageType = damageType;
        this.weight = weight;
        this.info = info;
        this.profiles = profiles;
    }

    public String[] getProfiles() {
        return profiles;
    }
    public String getWeaponType() {
        return weaponType;
    }

    public int getCost() {
        return cost;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public Dice getDie() {
        return die;
    }

    public String getDieString() {
        return (die != null) ? die.name() : "";
    }

    public void setDieFromString(String dieString){
        this.die = (dieString.isBlank()) ? null : Dice.valueOf(dieString);
    }

    public int getDiceMulti() {
        return diceMulti;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public String getDamageTypeString() {
        return (damageType != null) ? damageType.name() : "";
    }
    public void setDamageTypeFromString(String damageString){
        this.damageType = (damageString.isBlank()) ? null : DamageType.valueOf(damageString);
    }

    public double getWeight() {
        return weight;
    }

    public String getInfo() {
        return info;
    }
}