package attendance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    public static List<String> readCSV(String path) {
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 파일 포멧입니다.");
        }
    }
}
