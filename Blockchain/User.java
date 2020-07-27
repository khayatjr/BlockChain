import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;

public class User {

	public int userID;
	public ArrayList<Coin> coin;
	private PrivateKey privatekey;
	public PublicKey publickey;
	
	public User(int userID) throws Exception {
		this.userID = userID;
		this.coin = new ArrayList<Coin>();
		KeyPair kp = generateKeyPair();
		this.privatekey = kp.getPrivate();
		this.publickey = kp.getPublic();
				
	}
	
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public ArrayList<Coin> getCoin() {
		return coin;
	}

	public void setCoin(ArrayList<Coin> coin) {
		this.coin = coin;
	}

	public PrivateKey getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(PrivateKey privatekey) {
		this.privatekey = privatekey;
	}

	public PublicKey getPublickey() {
		return publickey;
	}

	public void setPublickey(PublicKey publickey) {
		this.publickey = publickey;
	}
    
}

