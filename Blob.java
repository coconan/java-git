import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Blob extends Object {
    private Byte[] content;
    private BigInteger oid;
    
    public Blob(Byte[] content) {
        this.content = content;
    }

    private static String byteArrayToString(Byte[] content) {
        byte[] bytes = new byte[content.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = content[i];
        }
        try {
            return new String(bytes, "utf8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return String.format("blob %d\0%s", content.length, byteArrayToString(content));
    }

    public byte[] getByteArray() {
        try {
            return toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
