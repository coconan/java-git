import java.io.UnsupportedEncodingException;

public class Blob {
    private Byte[] content;

    public Blob(Byte[] content) {
        this.content = content;
    }

    private static String byteArrayToString(Byte[] content) throws UnsupportedEncodingException {
        byte[] bytes = new byte[content.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = content[i];
        }
        return new String(bytes, "utf8");
    }

    public String toString() {
        try {
            return String.format("blob %d\0%s", content.length, byteArrayToString(content));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
