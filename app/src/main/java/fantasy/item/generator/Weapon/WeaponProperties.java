package fantasy.item.generator.Weapon;


import javax.persistence.Entity;
import javax.persistence.Id;

import fantasy.item.generator.Data.DataHelpers.DamageType;
import fantasy.item.generator.Data.DataHelpers.Dice;

@Entity
public class WeaponProperties {
    @Id
    private String weaponType;
    private int cost;
    private String costUnit;
    private Dice die;
    private int diceMulti;
    private DamageType damageType;
    private double weight;
    private String info;

    public WeaponProperties(){};

    public WeaponProperties(String weaponType, int cost, String costUnit,
            Dice die, int diceMulti, DamageType damageType, double weight, String info) {
        this.weaponType = weaponType;
        this.cost = cost;
        this.costUnit = costUnit.toUpperCase();
        this.die = die;
        this.diceMulti = diceMulti;
        this.damageType = damageType;
        this.weight = weight;
        this.info = info;
    }

    // public String getSQLInsertString(){
    //     String columnNames = "(weaponType,cost,costUnit,die,diceMulti,damageType,weight,info)";
    //     String values = "(?,?,?,?,?,?,?,?)";
    //     String sql = "INSERT INTO %s"+ columnNames + " VALUES" + values;
    //     return sql;
    // }

    // public List<String> replaceSQLEntry(){
    //     return Arrays.asList(new String[]{
    //         "DELETE FROM %s WHERE weaponType='"+ weaponType +"'",
    //         getSQLInsertString()
    //     });
    // }

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