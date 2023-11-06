package fantasy.item.generator.Weapon;

import fantasy.item.generator.Data.DataHelpers.MartialMeleeWeaponType;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import javax.persistence.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class MartialMeleeWeapon extends AbstractWeapon {
    private MartialMeleeWeaponType meleeWeapon;

    public MartialMeleeWeapon(){};

    public MartialMeleeWeapon(MartialMeleeWeaponType weapon){
        meleeWeapon = weapon;
    }

    @Override
    public void setName(String name) {
        setItemName(name);
    }

    @Override
    public String generateName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateName'");
    }

    @Override
    public String entityAsString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
        
    }

    @Override
    public String getMinorWeaponType() {
        return meleeWeapon.name().replaceAll("_", " ");
    }

    public void setMinorWeaponType(MartialMeleeWeaponType martialMeleeWeaponType) {
        meleeWeapon = martialMeleeWeaponType;
        setWeaponType(WeaponType.MartialMeleeWeaponType);

    }

}
