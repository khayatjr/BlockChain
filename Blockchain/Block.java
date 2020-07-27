import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class Block {

	private Date Timestamp;
	private String hash;
	private String previousHash;
	private ArrayList<Transaction> transaction;
	
	public Block(Date timestamp, ArrayList<Transaction> transaction) {
		this.Timestamp = timestamp;
		this.transaction = transaction;
		this.hash = computeHash();
	}
	
	public String computeHash() {
		
		String dataToHash = "" + this.Timestamp + this.previousHash;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.hash = encoded;
		return encoded;
		
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<Transaction> transaction) {
		this.transaction = transaction;
	}



	
	
	
	
}
