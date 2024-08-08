import java.time.LocalDateTime;

public class Post {
    private static int counter = 0;
    private int id;
    private int userId;
    private String content;
    private LocalDateTime timestamp;
    private int votes;


    public Post(int userId, String content) {
        this.id = ++counter;
        this.userId = userId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.votes = 0;
    }

    public void addVote(){
        votes++;
    }

    public void editContent(String newContent){
        this.content = newContent;
    }

    public void delete(){
        this.content = null;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getVotes() {
        return votes;
    }
}
