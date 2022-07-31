import java.io.File;
import java.nio.file.Path;

public class Workspace {
    private Path path;

    public Workspace(Path path) {
        this.path = path;
    }

    public File[] getFiles() {
        return path.toFile().listFiles();
    }
}
