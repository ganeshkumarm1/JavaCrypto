
/**
 *
 * @author GaNeShKuMaRm
 */
import java.security.*;
import java.math.*;

class MD5HASH
{
    public String calculateMD5(String password) throws Exception
    {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(password.getBytes(),0,password.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
}
