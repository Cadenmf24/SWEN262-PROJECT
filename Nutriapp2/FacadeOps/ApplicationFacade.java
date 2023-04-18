package Nutriapp2.FacadeOps;

public class ApplicationFacade {
    private UserManager userManager;
    private SessionManager sessionManager;
    private FeatureManager featureManager;

    public ApplicationFacade() {
        userManager = new UserManager();
        sessionManager = new SessionManager();
        featureManager = new FeatureManager();
    }

    public void register(String username, String password) {
        userManager.register(username, password);
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        userManager.changePassword(username, oldPassword, newPassword);
    }

    public void sendTeamRequest(String username, String otherUsername) {
        userManager.sendTeamRequest(username, otherUsername);
    }

    public void acceptTeamRequest(String username, String otherUsername) {
        userManager.acceptTeamRequest(username, otherUsername);
    }

    public void leaveTeam(String username) {
        userManager.leaveTeam(username);
    }

    public void login(String username, String password) {
        try {
            sessionManager.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            sessionManager.logout(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accessAllFeatures() {
        featureManager.accessAllFeatures();
    }
}
