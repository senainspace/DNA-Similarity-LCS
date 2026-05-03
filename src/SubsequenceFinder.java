import java.util.*;

public class SubsequenceFinder {

    private final LCS lcsObj;

    public SubsequenceFinder(LCS lcsObj) {
        this.lcsObj = lcsObj;
    }

    public List<String> find() {
        String s1 = lcsObj.getS1();
        String s2 = lcsObj.getS2();

        List<String> result = new ArrayList<>();
        result.add(lcsObj.reconstruct());           // 1. Standard LCS
        result.add(nucleotideGreedy(s1, s2, "CG"));  // 2. CG greedy (only C/G from s1)
        result.add(greedyForward(s1, s2));          // 3. Greedy: s1 left-to-right through s2
        result.add(greedyReverse(s1, s2));          // 4. Greedy: s1 right-to-left through s2
        result.add(nucleotideGreedy(s1, s2, "AG")); // 5. Purines only (A/G)
        result.add(nucleotideGreedy(s1, s2, "CT")); // 6. Pyrimidines only (C/T)
        return result;
    }

    // For each char in s1 (left to right), find its next occurrence in s2.
    // Result is a common subsequence of both strings but not derived from LCS.
    private String greedyForward(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        for (int i = 0; i < s1.length() && pos < s2.length(); i++) {
            char c = s1.charAt(i);
            int found = s2.indexOf(c, pos);
            if (found != -1) {
                sb.append(c);
                pos = found + 1;
            }
        }
        return sb.toString();
    }

    // For each char in s1 (right to left), find its previous occurrence in s2, then reverse.
    private String greedyReverse(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int pos = s2.length() - 1;
        for (int i = s1.length() - 1; i >= 0 && pos >= 0; i--) {
            char c = s1.charAt(i);
            int found = s2.lastIndexOf(c, pos);
            if (found != -1) {
                sb.append(c);
                pos = found - 1;
            }
        }
        return sb.reverse().toString();
    }

    // Greedy forward using only nucleotides in the allowed set.
    private String nucleotideGreedy(String s1, String s2, String allowed) {
        StringBuilder filtered = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (allowed.indexOf(c) >= 0) filtered.append(c);
        }
        return greedyForward(filtered.toString(), s2);
    }

    public static String[] labels() {
        return new String[]{
            "Standard LCS (DP backtrack, tie-break: prefer row move)",
            "CG greedy (only C/G from s1 matched through s2)",
            "Greedy forward (s1 chars matched left-to-right through s2)",
            "Greedy reverse (s1 chars matched right-to-left through s2)",
            "Purine greedy (only A/G from s1 matched through s2)",
            "Pyrimidine greedy (only C/T from s1 matched through s2)"
        };
    }
}
