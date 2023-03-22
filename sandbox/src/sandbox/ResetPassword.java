package sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class ResetPassword {

    /*
    DBT Labs interview:

What we are implementing is a two-step password reset process. Here are the
two steps:
1. Request a password reset via the `request_password_reset` method. This
   method stores a verification code and returns it.
2. Reset the password by calling `reset_password`. You need to provide the
   verification code from the previous step.
Here are the functional requirements:
1. The password can only be reset if the verification code passed to reset_password
   matches the one stored for a given user.
2. A user can only have one pending verification code at a time.
3. A verification code cannot be transferred to another user.
4. A verification code can only be used once.
5. A new password must be different than the old password.
A few more tips:
- the code is a work in progress, you should feel free to modify any part of it
- you can go through the `main` method to understand the flow we're trying
  to implement
- there are helper methods on the UserManager class that you can use */


    static class ResetRequest {
        private String userId;
        private String code;

        public ResetRequest(String userId, String code) {
            this.userId = userId;
            this.code = code;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getCode() {
            return this.code;
        }
    }

    static class User {
        private String userId;
        private String password;

        public User(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String pswd) {
            this.password = pswd;
        }
    }

    static class UserManager {
        // List<ResetRequest> resetRequests;
        Map<String, ResetRequest> resetRequests;
        List<User> userStore;

        public UserManager() {
            userStore = new ArrayList<>();
            userStore.add(new User("Abby", "old password"));
            userStore.add(new User("Brian", "password123"));
            resetRequests = new HashMap<>();
        }

        //to fill this method
        public String requestPasswordReset(String userId) {
            User user = userStore.stream()
                    .filter(u -> userId.equals(u.getUserId()))
                    .findAny()
                    .orElse(null);
            if (user == null) {
                return null;
            }
            String code = generateCode();
            ResetRequest request = new ResetRequest(userId, code);
            resetRequests.put(userId, request);
            return code;
        }

        public boolean resetPassword(String userId, String code, String newPassword) {
            User user = userStore.stream()
                    .filter(u -> userId.equals(u.getUserId()))
                    .findAny()
                    .orElse(null);
            if (user == null) {
                return false;
            }
            // ResetRequest request = resetRequests.stream()
            //   .filter(r -> userId.equals(r.getUserId()))
            //   .findAny()
            //   .orElse(null);
            ResetRequest request = resetRequests.get(userId);
            if (request == null) {
                return false;
            }
            if (!request.getCode().equals(code)) {
                return false;
            }
            if (user.getPassword().equals(newPassword)) {
                return false;
            }

            user.setPassword(newPassword);
            resetRequests.remove(userId);
            return true;
        }

        public boolean isPasswordMatching(String userId, String passwordToCompare) {
            User user = userStore.stream()
                    .filter(u -> userId.equals(u.getUserId()))
                    .findAny()
                    .orElse(null);

            return user.getPassword() == passwordToCompare;
        }
    }

    private static String generateCode() {
        // generates a random string of 32 chars
        return UUID.randomUUID().toString().replaceAll("_", "");
    }

        public static void main(String[] args) {
            UserManager um = new UserManager();
            String userId = "Abby";
            String code = um.requestPasswordReset(userId);
            um.resetPassword(userId, code, "new password");
            System.out.println(um.isPasswordMatching(userId, "new password")); //should print true

    }
}
