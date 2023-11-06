package fantasy.item.generator.Weapon;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fantasy.item.generator.Data.DataHelpers.SimpleRangeWeaponType;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import javax.persistence.Entity;

@Entity
public class SimpleRangeWeapon extends AbstractWeapon {
    SimpleRangeWeaponType rangedWeapon;

    public SimpleRangeWeapon(SimpleRangeWeaponType weapon){
        rangedWeapon = weapon;
    }

    public SimpleRangeWeapon() {
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
        return rangedWeapon.name().replaceAll("_", " ");
    }

    public void setMinorWeaponType(SimpleRangeWeaponType weapon) {
        rangedWeapon = weapon;
        setWeaponType(WeaponType.SimpleRangeWeaponType);
    }

}
