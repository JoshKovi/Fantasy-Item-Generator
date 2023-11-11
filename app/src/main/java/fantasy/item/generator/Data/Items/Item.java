package fantasy.item.generator.Data.Items;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import fantasy.item.generator.Data.Attributes.DataHelpers.GPType;
import fantasy.item.generator.Data.Attributes.Rarity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String name;
    private Rarity rarity;
    private double gpValue;
    private int cost;
    private GPType gpUnit;
    private double weight;
    private String weightUnits = "lbs"; //Default but overridable
    private Integer roll = -100; 
    private String[] profiles;

    public static int getRandomInt(int maxBoundInclusive){
        return new Random().nextInt(maxBoundInclusive);
    }

    // Abstract  Methods
    abstract public void generateName();

    // Custom setters
    public void setGpValue(double gp){
        double gpMulti = 0;
        switch(gpUnit){
            case GP:
                gpMulti = 1.0f;
                break;
            case SP:
                gpMulti = 0.01f;
                break;
            case CP:
                gpMulti = 0.0001f;
                break;
        }
        this.gpValue = gp * gpMulti;
    }

    public void setCost(int cost){
        this.cost = cost;
        setGpValue(cost);
    }

    // Default Setters
    public void setProfiles(String[] profiles) {
        this.profiles = profiles;
    }
    public void setName(String name){
        this.name = name;
    };
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void setRoll(int roll){
        this.roll = roll;
    }

    public void setItemName(String name){
        this.name = name;
    }
  
    public void setGPUnit(GPType type){
        this.gpUnit = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    // Getters
    public String[] getProfiles() {
        return profiles;
    }
    public String getName() {
        return this.name;
    }
    public Integer getRoll(){
        return this.roll;
    }
    public Rarity getRarity() {
        return this.rarity;
    }
        public int getCost(){
        return this.cost;
    }
    public double getGpValue(){
        return this.gpValue;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getWeightUnits() {
        return this.weightUnits;
    }
}
