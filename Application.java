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
        Path path = Paths.get(".");
        Path gitPath = Paths.get(".git");        
        Path databasePath = gitPath.resolve("objects");

        Workspace workspace = new Workspace(path);
        Database database = new Database(databasePath);
        
        if (!gitPath.toFile().exists()) {
            Files.createDirectory(gitPath);
        }
        if (!databasePath.toFile().exists()) {
            Files.createDirectory(databasePath);
        }

        // write blobs
        List<Entry> entries = createEntries(workspace);
        for (Entry entry : entries) {
            database.store(entry.getBlob());
        }

        // write tree
        Tree tree = new Tree(entries);
        database.store(tree);

        // write commit
        Commit commit = new Commit(tree);
        database.store(commit);

        System.out.println(String.format("%040x", commit.getOid()));
    }

    private static List<Entry> createEntries(Workspace workspace) throws IOException {
        File[] files = workspace.getFiles();
    
        List<Entry> entries = new ArrayList<>();
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
                entries.add(new Entry(new Blob(contentBytes), file.getName()));    
            }
        }

        return entries;
    }

    private static boolean isIgnoreFile(File file) {
        Set<String> ignoreSet = new HashSet<>();
        Collections.addAll(ignoreSet, "out", ".git");
        return ignoreSet.contains(file.getName());
    }
}
