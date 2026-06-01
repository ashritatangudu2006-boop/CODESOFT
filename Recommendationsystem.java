import java.util.*;

public class RecommendationSystem {
    // Sample dataset
    static String[] movies = {
            "Inception", "Interstellar", "The Dark Knight", "Avengers", "Titanic"
    };

    static String[] genres = {
            "Sci-Fi Thriller", "Sci-Fi Adventure", "Action Crime", "Action Superhero", "Romance Drama"
    };

    // Function to calculate similarity (simple overlap of words in genres)
    public static double similarity(String genre1, String genre2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(genre1.toLowerCase().split(" ")));
        Set<String> set2 = new HashSet<>(Arrays.asList(genre2.toLowerCase().split(" ")));

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size(); // Jaccard similarity
    }

    // Recommend movies based on similarity
    public static void recommend(String movieName) {
        int index = -1;
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].equalsIgnoreCase(movieName)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Movie not found in dataset.");
            return;
        }

        System.out.println("Recommendations for " + movieName + ":");
        Map<String, Double> scores = new HashMap<>();

        for (int i = 0; i < movies.length; i++) {
            if (i != index) {
                double score = similarity(genres[index], genres[i]);
                scores.put(movies[i], score);
            }
        }

        // Sort by similarity score
        scores.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(2) // top 2 recommendations
                .forEach(entry -> System.out.println(entry.getKey() + " (score: " + entry.getValue() + ")"));
    }

    public static void main(String[] args) {
        recommend("Inception");
        recommend("Titanic");
    }
}
