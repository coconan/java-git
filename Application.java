import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) throws Exception {
        Path gitPath = Paths.get(".git");        
        Path databasePath = gitPath.resolve("objects");
        
        Database database = new Database(databasePath);
        
        if (!gitPath.toFile().exists()) {
            Files.createDirectory(gitPath);
        }
        if (!databasePath.toFile().exists()) {
            Files.createDirectory(databasePath);
        }

        List<Blob> blobs = createBlobs();

        for (Blob blob : blobs) {
            database.store(blob);
        }
    }

    private static List<Blob> createBlobs() throws IOException {
        Path wd = Paths.get(".");
        File[] files = wd.toFile().listFiles();
    
        List<Blob> blobs = new ArrayList<>();
        for (File file : files) {
            if (isIgnoreFile(file)) {
                continue;
            }
            List<Byte> content = new ArrayList<>();
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] ba = new byte[1024];
                int length = 0;
                while ((length = fis.read(ba)) > 0) {
                    for (int i = 0; i < length; i++) {
                        content.add(ba[i]);                    
                    }
                }
                Byte[] contentBytes = content.toArray(new Byte[0]);
                blobs.add(new Blob(contentBytes));    
            }
        }

        return blobs;
    }

    private static boolean isIgnoreFile(File file) {
        Set<String> ignoreSet = new HashSet<>();
        Collections.addAll(ignoreSet, "out", ".git");
        return ignoreSet.contains(file.getName());
    }
}
