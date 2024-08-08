import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private String name;
    private List<Post> posts;
    private Set<Integer> votesGiven;
    private List<Contest> contestParticipation;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.posts = new ArrayList<>();
        this.votesGiven = new HashSet<>();
        this.contestParticipation = new ArrayList<>();
    }

    public void postContent(Contest contest, String content) {
        Post post = new Post(this.id,content);
        posts.add(post);
        contest.addPost(post);
    }

    public void editPost(Post post, String newContent){
        if(post.getUserId() == this.id && Duration.between(post.getTimestamp(), LocalDateTime.now()).toDays()<=3){
            post.editContent(newContent);
        }
        else{
            System.out.println("Can't edit post");
        }
    }

    public void deletePost(Contest contest, Post post){
        if(post.getUserId() == this.id && Duration.between(post.getTimestamp(), LocalDateTime.now()).toDays()<=3){
            post.delete();
            posts.remove(post);
            contest.removePost(post);
        }
        else {
            System.out.println("Can't delete post");
        }
    }

    public void vote(Post post){
        if(!votesGiven.contains(post.getId())){
            post.addVote();
            votesGiven.add(post.getId());
        }
    }

    public void  joinContest(Contest contest){
        contest.addParticipant(this);
        contestParticipation.add(contest);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<Integer> getVotesGiven() {
        return votesGiven;
    }

    public List<Contest> getContestParticipation() {
        return contestParticipation;
    }
}
