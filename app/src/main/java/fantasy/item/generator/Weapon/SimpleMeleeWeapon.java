package fantasy.item.generator.Weapon;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fantasy.item.generator.Data.DataHelpers.SimpleMeleeWeaponType;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import javax.persistence.Entity;

@Entity
public class SimpleMeleeWeapon extends AbstractWeapon {
    private SimpleMeleeWeaponType meleeWeapon;

    public SimpleMeleeWeapon(SimpleMeleeWeaponType weapon){
        meleeWeapon = weapon;
    }

    public SimpleMeleeWeapon() {
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

    public void setMinorWeaponType(SimpleMeleeWeaponType weapon) {
        meleeWeapon = weapon;
        setWeaponType(WeaponType.SimpleMeleeWeaponType);
    }

}
