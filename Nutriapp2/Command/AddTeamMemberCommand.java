package Command;

import UserProfile.User;

public class AddTeamMemberCommand implements Command {
    private User user;
    private User teamMember;

    public AddTeamMemberCommand(User user2, User user3) {
        this.user = user2;
        this.teamMember = user3;
    }

    @Override
    public void execute() {
        user.addTeamMember(teamMember);
    }

    @Override
    public void undo() {
        user.removeTeamMember(teamMember);
    }
}
