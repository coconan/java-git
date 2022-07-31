public class Commit extends Object {
    private Tree tree;

    public Commit(Tree tree) {
        this.tree = tree;
    }

    @Override
    public byte[] getByteArray() {
        return "commit ".getBytes();
    }
}
