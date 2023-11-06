package fantasy.item.generator.Data;

import java.util.Arrays;
import java.util.List;

public class LoginData {

    private static final String DEFAULT = "Default";

    private List<String> userProfiles;
    private String selectedProfile;
    private ProfileData profileData;

    public LoginData() {
        retrieveUserProfiles();

    }

    public void retrieveUserProfiles() {
        // TODO: Replace with backing from SQL or Mongo user Profiles are simply used
        // for default settings and info, default should
        // have a default setting (adjustable/revertable) with all overall items (no
        // exclusions)
        userProfiles = Arrays.asList(new String[] { DEFAULT, "Josh", "Jerry", "Corey", "Weir", "Webster", "ShaBoner",
                "Jackie the Toe-Fucker" });
    }

    public void loadFromProfile() {
        if (selectedProfile.equals(DEFAULT)) {
            userProfiles.forEach((profile) -> {
                if (profile.equals(DEFAULT))
                    return;

            });
        }
    }

    public List<String> getUserProfiles() {
        return userProfiles;
    }
}
