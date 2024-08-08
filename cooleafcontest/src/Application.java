import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Application {
    private List<User> users;
    private List<Contest> contests;

    public Application() {
        users = new ArrayList<>();
        contests = new ArrayList<>();
    }

    public User registerUser(String name) {
        User user = new User(users.size() + 1, name);
        users.add(user);
        return user;
    }

    public Contest createContest(String name, LocalDateTime startDate, LocalDateTime endDate, double prizePool) {
        Contest contest = new Contest(contests.size() + 1, name, startDate, endDate, prizePool);
        contests.add(contest);
        return contest;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Contest getContestById(int id) {
        for (Contest contest : contests) {
            if (contest.getId() == id) {
                return contest;
            }
        }
        return null;
    }

}
