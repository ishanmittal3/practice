package sandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VersionedProfiles {

    private Map<String, List<Map<String, String>>> profiles;

    public VersionedProfiles() {
        profiles = new HashMap<>();
    }

    public void update(String profileId, String fieldName, String fieldValue) {
        List<Map<String, String>> versions = profiles.get(profileId);
        if (versions == null) {
            versions = new ArrayList<>();
            Map<String, String> newProfile = new HashMap<>();
            newProfile.put(fieldName, fieldValue);
            versions.add(newProfile);
            profiles.put(profileId, versions);
        } else {
            Map<String, String> newProfile = new HashMap<>(versions.get(versions.size() - 1));
            newProfile.put(fieldName, fieldValue);
            versions.add(newProfile);
        }
    }

    public void get(String profileId, int versionId) {
        System.out.println("Profile for " + profileId + " at version " + versionId + ":");
        if (!profiles.containsKey(profileId)) {
            System.out.println("Invalid request!");
            return;
        }
        List<Map<String, String>> versions = profiles.get(profileId);
        if ((versionId <= 0) || (versionId > versions.size())) {
            System.out.println("Invalid request!");
            return;
        }
        Map<String, String> profile = versions.get(versionId-1);
        List<String> fields = new ArrayList<>(profile.keySet());
        Collections.sort(fields);
        for (String field : fields) {
            System.out.println(field + " - " + profile.get(field));
        }
    }

    public void getField(String profileId, int versionId, String fieldName) {
        System.out.println(fieldName + " for " + profileId + " at version " + versionId + ":");
        if (!profiles.containsKey(profileId)) {
            System.out.println("Invalid request!");
            return;
        }
        List<Map<String, String>> versions = profiles.get(profileId);
        if ((versionId <= 0) || (versionId > versions.size())) {
            System.out.println("Invalid request!");
            return;
        }
        Map<String, String> profile = versions.get(versionId-1);
        String value = profile.get(fieldName);
        if (value == null) {
            System.out.println("Invalid request!");
            return;
        }
        System.out.println(value);
    }


    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        VersionedProfiles sol = new VersionedProfiles();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String queryType = in.next();
            String profileId = in.next();
            if ("update".equals(queryType)) {
                String fieldName = in.next();
                String fieldValue = in.next();
                sol.update(profileId, fieldName, fieldValue);
            } else if ("get".equals(queryType)) {
                int version = in.nextInt();
                sol.get(profileId, version);
            } else if ("getfield".equals(queryType)) {
                int version = in.nextInt();
                String fieldName = in.next();
                sol.getField(profileId, version, fieldName);
            }
        }
    }
}
