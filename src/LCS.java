public class LCS {

    private final String s1;
    private final String s2;
    private final short[][] dp;
    private String lcsCache;

    public LCS(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.dp = new short[s1.length() + 1][s2.length() + 1];
        buildTable();
    }

    private void buildTable() {
        int m = s1.length();
        int n = s2.length();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = (short) (dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j] >= dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
    }

    public int getLCSLength() {
        return dp[s1.length()][s2.length()] & 0xFFFF;
    }

    public String reconstruct() {
        if (lcsCache != null) return lcsCache;

        StringBuilder sb = new StringBuilder();
        int i = s1.length();
        int j = s2.length();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if ((dp[i - 1][j] & 0xFFFF) >= (dp[i][j - 1] & 0xFFFF)) {
                i--;
            } else {
                j--;
            }
        }

        lcsCache = sb.reverse().toString();
        return lcsCache;
    }

    public String reconstructAlt() {
        StringBuilder sb = new StringBuilder();
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if ((dp[i - 1][j] & 0xFFFF) > (dp[i][j - 1] & 0xFFFF)) {
                i--;
            } else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    public String getS1() { return s1; }
    public String getS2() { return s2; }
    public short[][] getTable() { return dp; }
}
