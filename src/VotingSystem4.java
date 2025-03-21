import java.util.*;

public class VotingSystem4 {
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

            if (!scanner.hasNextInt()) { // If user enters non-numeric input
                System.out.println("Invalid input! Please enter a number (1-3).");
                scanner.next(); // Consume the invalid input
                continue;
            }

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
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    private static void vote(Scanner scanner) {
        System.out.print("\nEnter Voter ID: ");
        String voterID = scanner.nextLine().trim();

        if (hasVoted.getOrDefault(voterID, false)) {
            System.out.println("You have already voted! Next voter, please.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        voters.put(voterID, name);
        hasVoted.put(voterID, false);

        System.out.println("\nCandidates:");
        System.out.println("1. TDP");
        System.out.println("2. YCP");
        System.out.println("3. NONE");

        int choice;
        while (true) { // Loop until user enters a valid choice
            System.out.print("Enter Candidate Number (1-3) to vote: ");
            if (!scanner.hasNextInt()) { // If input is not a number
                System.out.println("Invalid input! Please enter 1, 2, or 3.");
                scanner.next(); // Consume invalid input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice >= 1 && choice <= 3) {
                break; // Exit loop if input is valid
            } else {
                System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }

        String selectedCandidate = switch (choice) {
            case 1 -> "TDP";
            case 2 -> "YCP";
            case 3 -> "NONE";
            default -> null;
        };

        votes.put(selectedCandidate, votes.get(selectedCandidate) + 1);
        hasVoted.put(voterID, true);

        System.out.println("\nVoter Details:");
        System.out.println("Voter ID: " + voterID);
        System.out.println("Name: " + name);
        System.out.println("You have successfully voted for: " + selectedCandidate);
    }

    private static void displayResults() {
        System.out.println("\n--- Election Results ---");
        int highestVotes = Collections.max(votes.values());

        // Find all candidates with the highest votes
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            if (entry.getValue() == highestVotes) {
                winners.add(entry.getKey());
            }
        }

        // Determine the winner
        if (winners.size() == 1) {
            System.out.println("\n🏆 Winner: " + winners.get(0) + " 🎉");
        } else {
            System.out.println("\n🤝 It's a tie between: " + String.join(", ", winners) + "!");
        }
    }
}

