package fantasy.item.generator.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import fantasy.item.generator.Data.DataHelpers.GPType;
import fantasy.item.generator.Data.DataHelpers.Rarity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dataEntryString;
    private Rarity rarity;
    private double gpValue;
    private int cost;
    private GPType gpUnit;
    private double weight;
    private String weightUnits = "lbs"; //Default but overridable
    private Integer roll;

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

    // Abstract  Methods
    abstract public void setName(String name);

    abstract public String generateName(String name);

    abstract public String entityAsString() throws JsonProcessingException;

}
