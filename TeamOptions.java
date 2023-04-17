public interface TeamOptions {
    
    public void joinTeam(User user);
    public void leaveTeam(User user);
    public void sendInvite(User sender, User receiver);
    public void viewHistory(User user);
    public void logWorkout();

    public void sendNotification();

    public void setChallenge(Integer minutes);
    public void viewRanking();

}
