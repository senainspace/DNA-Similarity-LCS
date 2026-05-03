import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // ── 1. Read DNA sequences ──────────────────────────────────────────────
        String humanDNA = DNAReader.read("data/homosapiens.txt");
        String chimpDNA  = DNAReader.read("data/chimpanzee.txt");

        System.out.println("=== DNA Sequence Comparison using LCS (Dynamic Programming) ===");
        System.out.println();
        System.out.printf("Human DNA length     : %,d characters%n", humanDNA.length());
        System.out.printf("Chimpanzee DNA length: %,d characters%n", chimpDNA.length());
        System.out.println();

        // ── 2. Build DP table & compute LCS ───────────────────────────────────
        System.out.println("Building LCS DP table... (this may take a few seconds)");
        long startTime = System.currentTimeMillis();

        LCS lcs = new LCS(humanDNA, chimpDNA);

        long buildMs = System.currentTimeMillis() - startTime;
        System.out.printf("DP table built in %d ms%n%n", buildMs);

        // ── 3. LCS length ──────────────────────────────────────────────────────
        int lcsLen = lcs.getLCSLength();
        System.out.printf("LCS Length: %,d%n", lcsLen);
        double similarity = (double) lcsLen / Math.min(humanDNA.length(), chimpDNA.length()) * 100;
        System.out.printf("Similarity (LCS / shorter sequence): %.2f%%%n%n", similarity);

        // ── 4. Reconstruct LCS via backtracking ────────────────────────────────
        System.out.println("Reconstructing LCS via backtracking...");
        long recStart = System.currentTimeMillis();
        String lcsString = lcs.reconstruct();
        long recMs = System.currentTimeMillis() - recStart;

        System.out.printf("Reconstruction done in %d ms%n", recMs);
        System.out.println();
        System.out.println("--- Full LCS ---");
        System.out.println(lcsString);
        System.out.println();

        // ── 5. Five different common subsequences ──────────────────────────────
        System.out.println("=== 5+ Different Common Subsequences ===");
        System.out.println();

        SubsequenceFinder finder = new SubsequenceFinder(lcs);
        List<String> subsequences = finder.find();
        String[] labels = SubsequenceFinder.labels();

        for (int i = 0; i < subsequences.size(); i++) {
            String seq = subsequences.get(i);
            System.out.printf("[%d] %s%n", i + 1, labels[i]);
            System.out.printf("    Length : %,d%n", seq.length());
            System.out.printf("    Preview: %s%n", seq.substring(0, Math.min(80, seq.length())));
            System.out.println();
        }

        System.out.println("=== Done ===");
    }
}
