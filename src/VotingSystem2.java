import java.util.HashMap;
import java.util.Scanner;

public class VotingSystem2 {
    private static HashMap<String, String> voters = new HashMap<>(); // Stores Voter ID and Name
    private static HashMap<String, Boolean> hasVoted = new HashMap<>(); // Tracks if voter has voted
    private static HashMap<String, Integer> votes = new HashMap<>(); // Stores votes per candidate

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize candidates with 0 votes
        votes.put("TDP", 0);
        votes.put("YCP", 0);
        votes.put("NONE", 0);

        while (true) {
            System.out.println("\n--- Online Voting System ---");
            System.out.println("1. Vote Now");
            System.out.println("2. Display Election Results");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    vote(scanner);
                    break;
                case 2:
                    displayResults();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you for participating!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method for Voting
    private static void vote(Scanner scanner) {
        System.out.print("\nEnter Voter ID: ");
        String voterID = scanner.nextLine();

        // Check if voter has already voted
        if (hasVoted.getOrDefault(voterID, false)) {
            System.out.println("You have already voted! Next voter, please.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        // Store voter details
        voters.put(voterID, name);
        hasVoted.put(voterID, false);

        // Display candidates
        System.out.println("\nCandidates:");
        System.out.println("1. TDP");
        System.out.println("2. YCP");
        System.out.println("3. NONE");

        // Vote Casting
        System.out.print("Enter Candidate Number (1-3) to vote: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String selectedCandidate = null;
        switch (choice) {
            case 1:
                selectedCandidate = "TDP";
                break;
            case 2:
                selectedCandidate = "YCP";
                break;
            case 3:
                selectedCandidate = "NONE";
                break;
            default:
                System.out.println("Invalid choice! Vote not recorded.");
                return;
        }

        // Increment vote count
        votes.put(selectedCandidate, votes.get(selectedCandidate) + 1);
        hasVoted.put(voterID, true); // Mark voter as voted

        // Display confirmation
        System.out.println("\nVoter Details:");
        System.out.println("Voter ID: " + voterID);
        System.out.println("Name: " + name);
        System.out.println("You have successfully voted for: " + selectedCandidate);

        // Display Updated Election Results
        displayResults();
    }

    // Method to Display Election Results
    private static void displayResults() {
        System.out.println("\n--- Election Results ---");
        for (String candidate : votes.keySet()) {
            System.out.println(candidate + ": " + votes.get(candidate) + " votes");
        }
    }
}