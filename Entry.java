public class Entry {
    private Blob blob;
    private String name;

    public Entry(Blob blob, String name) {
        this.blob = blob;
        this.name = name;
    }

    public Blob getBlob() {
        return blob;
    }

    public String getName() {
        return name;
    }
}
