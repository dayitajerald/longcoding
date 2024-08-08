import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Application app = new Application();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCooleaf Contest Application");
            System.out.println("1. Register User");
            System.out.println("2. Create Contest");
            System.out.println("3. Join Contest");
            System.out.println("4. Post Content");
            System.out.println("5. Edit Post");
            System.out.println("6. Delete Post");
            System.out.println("7. Vote Post");
            System.out.println("8. End Contest");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    createContest();
                    break;
                case 3:
                    joinContest();
                    break;
                case 4:
                    postContent();
                    break;
                case 5:
                    editPost();
                    break;
                case 6:
                    deletePost();
                    break;
                case 7:
                    votePost();
                    break;
                case 8:
                    endContest();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        app.registerUser(name);
        System.out.println("User registered successfully.");
    }

    private static void createContest() {
        System.out.print("Enter contest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contest start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter contest end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter prize pool: ");
        double prizePool = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        app.createContest(name, startDate.atStartOfDay(), endDate.atTime(23, 59), prizePool);
        System.out.println("Contest created successfully.");
    }

    private static void joinContest() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter contest ID: ");
        int contestId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = app.getUserById(userId);
        Contest contest = app.getContestById(contestId);

        if (user != null && contest != null) {
            user.joinContest(contest);
            System.out.println("User joined the contest successfully.");
        } else {
            System.out.println("Invalid user ID or contest ID.");
        }
    }

    private static void postContent() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter contest ID: ");
        int contestId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        User user = app.getUserById(userId);
        Contest contest = app.getContestById(contestId);

        if (user != null && contest != null) {
            user.postContent(contest, content);
            System.out.println("Content posted successfully.");
        } else {
            System.out.println("Invalid user ID or contest ID.");
        }
    }

    private static void editPost() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter post ID: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new content: ");
        String newContent = scanner.nextLine();

        User user = app.getUserById(userId);

        if (user != null) {
            Post post = user.getPosts().stream()
                    .filter(p -> p.getId() == postId)
                    .findFirst()
                    .orElse(null);

            if (post != null) {
                user.editPost(post, newContent);
                System.out.println("Post edited successfully.");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid user ID.");
        }
    }

    private static void deletePost() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter post ID: ");
        int postId = scanner.nextInt();
        System.out.print("Enter contest ID: ");
        int contestId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = app.getUserById(userId);
        Contest contest = app.getContestById(contestId);

        if (user != null && contest != null) {
            Post post = user.getPosts().stream()
                    .filter(p -> p.getId() == postId)
                    .findFirst()
                    .orElse(null);

            if (post != null) {
                user.deletePost(contest, post);
                System.out.println("Post deleted successfully.");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid user ID or contest ID.");
        }
    }

    private static void votePost() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter post ID: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = app.getUserById(userId);

        if (user != null) {
            Post post = null;
            for (Contest contest : user.getContestParticipation()) {
                post = contest.getPosts().stream()
                        .filter(p -> p.getId() == postId)
                        .findFirst()
                        .orElse(null);
                if (post != null) break;
            }

            if (post != null) {
                user.vote(post);
                System.out.println("Voted successfully.");
            } else {
                System.out.println("Post not found.");
            }
        } else {
            System.out.println("Invalid user ID.");
        }
    }

    private static void endContest() {
        System.out.print("Enter contest ID: ");
        int contestId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Contest contest = app.getContestById(contestId);

        if (contest != null) {
            if (contest.getEndDate().isBefore(LocalDateTime.now())) {
                contest.endContest();
                System.out.println("Contest ended and prizes distributed.");
            } else {
                System.out.println("Contest has not ended yet.");
            }
        } else {
            System.out.println("Invalid contest ID.");
        }
    }
}