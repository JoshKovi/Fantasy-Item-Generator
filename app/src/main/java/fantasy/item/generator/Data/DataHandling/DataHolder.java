package fantasy.item.generator.Data.DataHandling;

import java.util.Set;

public class DataHolder {

    private Set<String> profiles = Set.of("Defaults");

    private static DataHolder holder;


    private DataHolder(){}

    public static DataHolder getInstance(){
        if(holder == null){
            holder = new DataHolder();
        }
        return holder;
    }

    public void setProfiles(Set<String> profiles) {
        this.profiles = profiles;
    }
    public void addProfiles(Set<String> profiles) {
        this.profiles.addAll(profiles);
    }
    public Set<String> getProfiles() {
        return this.profiles;
    }

    
}
