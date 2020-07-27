import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class Transaction {

	public String transactionid;
	public String transactionHash;
	public String prevtransactionHash;
	public Date Timestamp;
	public Coin coin;
	public PublicKey SenderPublicKey;
	public PublicKey ReceiverPublicKey;
	
	
	public Transaction(String transactionid, Coin coin, PublicKey SenderPublicKey, PublicKey ReceiverPublicKey) {
		this.transactionid = transactionid;
		this.transactionHash = computeHash();
		this.coin = coin;
		this.SenderPublicKey = SenderPublicKey;
		this.ReceiverPublicKey = ReceiverPublicKey;
		
	}
	
public String computeHash() {
		
		String dataToHash = "" + this.transactionid + this.Timestamp;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.transactionHash = encoded;
		//System.out.println(encoded);
		return encoded;
		
	}




	
	
}
