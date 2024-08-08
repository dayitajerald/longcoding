import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Contest {
    private int id;
    private String name;
    private List<User> participants;
    private List<Post> posts;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double prizePool;

    public Contest(int id, String name, LocalDateTime startDate, LocalDateTime endDate, double prizePool) {
        this.id = id;
        this.name = name;
        this.participants = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.prizePool = prizePool;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }


    public void removePost(Post post) {
        posts.remove(post);
    }

    public void endContest() {
        distributePrizes();
    }

    private void distributePrizes() {
        posts.sort(Comparator.comparingInt(Post::getVotes).reversed());
        int totalWinners = Math.min(3, posts.size());
        double prize = prizePool / totalWinners;
        for (int i = 0; i < totalWinners; i++) {
            Post post = posts.get(i);
            System.out.println("User " + post.getUserId() + " wins $" + prize + " for post " + post.getId());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getPrizePool() {
        return prizePool;
    }
}

