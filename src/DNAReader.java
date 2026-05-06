import java.io.*;

public class DNAReader {

    public static String read(String filePath) throws IOException {
        File resolvedFile = resolveFile(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(resolvedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
        }
        return sb.toString();
    }

    private static File resolveFile(String filePath) throws FileNotFoundException {
        File direct = new File(filePath);
        if (direct.exists()) {
            return direct;
        }

        File nestedInProjectFolder = new File("DNA-Similarity-LCS", filePath);
        if (nestedInProjectFolder.exists()) {
            return nestedInProjectFolder;
        }

        throw new FileNotFoundException(filePath + " (veya DNA-Similarity-LCS/" + filePath + ") bulunamadı");
    }
}
