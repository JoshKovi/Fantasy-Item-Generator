package fantasy.item.generator.Weapon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fantasy.item.generator.Data.DataHelpers.MartialRangeWeaponType;
import fantasy.item.generator.Data.DataHelpers.WeaponType;
import javax.persistence.Entity;

@Entity
public class MartialRangeWeapon extends AbstractWeapon {
    private MartialRangeWeaponType rangedWeapon;

    public MartialRangeWeapon(MartialRangeWeaponType weapon){
        rangedWeapon = weapon;
    }

    public MartialRangeWeapon() {
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

    public void setMinorWeaponType(MartialRangeWeaponType martialRangeWeaponType) {
        rangedWeapon = martialRangeWeaponType;
        setWeaponType(WeaponType.MartialRangeWeaponType);
    }

}
