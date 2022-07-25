import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Deflater;

public class Database {
    
    private Path path;

    public Database(Path path) {
        this.path = path;
    }

    public void store(Blob blob) throws NoSuchAlgorithmException, IOException {
        byte[] input = blob.toString().getBytes("UTF-8");
        byte[] output = new byte[input.length];
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    digest.reset();
	    digest.update(input);
	    String sha1 = String.format("%040x", new BigInteger(1, digest.digest()));

        Path dir = path.resolve(sha1.substring(0, 2));
        if (!dir.toFile().exists()) {
            Files.createDirectory(dir);
        }
        try (FileOutputStream fos = new FileOutputStream(dir.resolve(sha1.substring(2)).toFile())) {
            fos.write(output, 0, compressedDataLength);
    
        }
    }
}
