import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tree extends Object {
    private BigInteger oid;
    private List<Entry> entries;

    public Tree(List<Entry> entries) {
        this.entries = entries;
    }

    public byte[] getByteArray() {
        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry a, Entry b) {
                return a.getName().compareTo(b.getName());
            }
        });
        List<Byte> result = new ArrayList<>();
        for (Entry entry : entries) {
            for (byte b : String.format("100644 %s\0", entry.getName()).getBytes()) {
                result.add(b);
            }
            byte[] oidBytes = entry.getBlob().getOid().toByteArray();
            for (int i = Math.max(0, oidBytes.length-20); i < oidBytes.length; i++) {
                // if (entry.getName().equals("Application.java")) {
                //     System.out.printf("%x ", oidBytes[i]);
                // }
                result.add(oidBytes[i]);
            }
            byte b = 0;
            for (int i = 0; i < 20 - oidBytes.length; i++) {
                result.add(b);
            }
        }
        byte[] header = String.format("tree %d\0", result.size()).getBytes();
        byte[] bytes = new byte[header.length + result.size()];
        for (int i = 0; i < header.length; i++) {
            bytes[i] = header[i];
        }
        for (int i = 0; i < result.size(); i++) {
            bytes[i+header.length] = result.get(i);
        }
        return bytes;
    }
}