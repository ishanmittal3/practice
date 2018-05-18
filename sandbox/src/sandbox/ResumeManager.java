package sandbox;

import java.util.*;

// Change this class however you want. Print your output to STDOUT
public class ResumeManager {

    private Map<String, List<Map<String, String>>> profiles;

    public ResumeManager() {
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
        if (versionId >= versions.size()) {
            System.out.println("Invalid request!");
            return;
        }
        Map<String, String> profile = versions.get(versionId);
        List<String> fields = new ArrayList<>(profile.keySet());
        Collections.sort(fields);
        for (String field : fields) {
            System.out.println(field + " - " + profile.get(field));
        }
    }

    public void getField(String profileId, int versionId, String fieldName) {
        System.out.println(fieldName + " for " + profileId + " at version " + versionId + ":");
        System.out.println("Profile for " + profileId + " at version " + versionId + ":");
        if (!profiles.containsKey(profileId)) {
            System.out.println("Invalid request!");
            return;
        }
        List<Map<String, String>> versions = profiles.get(profileId);
        if (versionId >= versions.size()) {
            System.out.println("Invalid request!");
            return;
        }
        Map<String, String> profile = versions.get(versionId);
        System.out.println(fieldName + " - " + profile.get(fieldName));
    }


    public static void main(String args[] ) throws Exception {
        ResumeManager sol = new ResumeManager();
        sol.update("pf1", "skills", "Java");
        sol.update("pf1", "education", "UT");
        sol.update("pf2", "workexperience", "Indeed");
        sol.update("pf1", "skills", "Java, Python");

        sol.get("pf2", 1);

        /*Scanner in = new Scanner(System.in);
        ResumeManager sol = new ResumeManager();
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
        }*/
    }
}
