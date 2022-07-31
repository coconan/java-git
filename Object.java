import java.math.BigInteger;

public abstract class Object {
    private BigInteger oid;
    
    public void setOid(BigInteger oid) {
        this.oid = oid;
    }

    public BigInteger getOid() {
        return oid;
    }

    public abstract byte[] getByteArray();
}
