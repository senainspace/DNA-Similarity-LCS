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
        result.add(nucleotideGreedy(s1, s2, "CG"));  // 1. CG greedy (only C/G from s1)
        result.add(greedyForward(s1, s2));          // 2. Greedy: s1 left-to-right through s2
        result.add(greedyReverse(s1, s2));          // 3. Greedy: s1 right-to-left through s2
        result.add(nucleotideGreedy(s1, s2, "AG")); // 4. Purines only (A/G)
        result.add(nucleotideGreedy(s1, s2, "CT")); // 5. Pyrimidines only (C/T)
        return result;
    }

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
            "CG greedy (only C/G from s1 matched through s2)",
            "Greedy forward (s1 chars matched left-to-right through s2)",
            "Greedy reverse (s1 chars matched right-to-left through s2)",
            "Purine greedy (only A/G from s1 matched through s2)",
            "Pyrimidine greedy (only C/T from s1 matched through s2)"
        };
    }
}
