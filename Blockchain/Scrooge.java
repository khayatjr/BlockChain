import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.sql.Timestamp;    
import java.util.Date;    


public class Scrooge {

	public static void main(String args[]) throws Exception {
		
		
		ArrayList<Transaction> BlockTransactions = new ArrayList<Transaction>();
		 ArrayList<String> hashes = new ArrayList<String>();
		
		Blockchain tcpCoin = new Blockchain();
		
		
		//tcpCoin.getLatestBlock().setPreviousHash("ABCDEFG");
		
	//	tcpCoin.displayChain();
		
	//	tcpCoin.isValid();
		
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Coin> coins = new ArrayList<Coin>();
		

		int z = 0;
		for(int i = 0; i< 100; i++) {
			
			User u = new User(i); 
			users.add(u);
			
			for(int j = 0; j< 10; j++) {
				
			Coin co = new Coin(z); 
			coins.add(co);
			z++;
		}
			u.coin = (ArrayList<Coin>) coins.clone();
			coins.clear();
			
		}//
		Random rn = new Random();
		//new Random().nextInt((max - min) + 1) + min
		int i = 0;
		String output = "";
				while(true){
					System.out.println("Press any key then Enter to continue / Press Space then Enter  to terminate");
					 BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
					 if(in.readLine().equals(" ")){
						 File newTextFile = new File(System.getProperty("user.dir")+"/output.txt");
				            FileWriter fw = new FileWriter(newTextFile);
				            fw.write(output);
				            fw.close();
						 System.exit(0);
					 }
//			 BufferedReader rd = new BufferedReader (new InputStreamReader(System.in));
//
//			 if( rd.readLine().equals(" ")) {
//			 				break;
//			 			}
				int RandomUser1 = rn.nextInt(100);
				int RandomUser2 = rn.nextInt(100);
				int RandomCoin = rn.nextInt(10);
				
				Transaction T = new Transaction("" + i, users.get(RandomUser1).getCoin().get(RandomCoin), users.get(RandomUser1).getPublickey(), users.get(RandomUser2).getPublickey());
				i++;
				String SignedTransaction = sign(T.transactionHash, users.get(RandomUser1).getPrivatekey());
				boolean verification = verify(T.transactionHash, SignedTransaction, users.get(RandomUser1).getPublickey());
				if (verification) {
				BlockTransactions.add(T);
				}
				//
			//	System.out.println("User: " + RandomUser1 + " Sent a coin to: User " + RandomUser2 );
			//	
				System.out.println("Transaction ID: " +  T.transactionid);
				System.out.println("Block Under Construction..");
				System.out.println();
				
				//
				if(BlockTransactions.size() == 10) {
					Timestamp ts=new Timestamp(System.currentTimeMillis());  
		            Date date=new Date(ts.getTime());
		        	Block block = new Block(date, BlockTransactions);
		        	hashes.add(block.getHash());
		        String root=MerkleTree.createMerkleTree(hashes);
		        System.out.println("Merkel root : "+root);
		        
		        	tcpCoin.addBlock(block);
		        	tcpCoin.displayChain();
		        	System.out.println("{");
		        	for (int j = 0; j< BlockTransactions.size(); j++) {
		        		System.out.println();
		        		System.out.println("Sender Public Key: " + BlockTransactions.get(j).SenderPublicKey);
						System.out.println("Receiver Public Key: " + BlockTransactions.get(j).ReceiverPublicKey);
						System.out.println("Previous Transaction Hash: " + BlockTransactions.get(j).prevtransactionHash);
						System.out.println("Transaction Hash: " + BlockTransactions.get(j).transactionHash);
						System.out.println("Transaction Hash: " + BlockTransactions.get(j).transactionid);
						System.out.println();
		        	}
		        	System.out.println("}");
		        	BlockTransactions.clear();
		        	
		        	
				}
			//	tcpCoin.displayChain();
				}
				
				
					
		   
				
				
		// }
		
	}
	//hat3mel 2 users, we hatedihom el id
	//method men google hatgeb rakamen random men 0 le 100 - hatgeeb random lel coins
	//el user el awalani hagblo coin random mn el array list bet3to
	//ha3ml transaction, w hadeha el public key bet3 user a we user b
	//check el project notes, ay hga scrooge verify or sign,, signing is 
	// transaction accumulator, awel ma el transactions yb2o 10, y2om 3amel block
	// block = new block , we hadeelo el transaction list
	// while true, terminate using space
	// merkle tree

			
	 public static String sign(String plainText, PrivateKey privateKey) throws Exception {
	        Signature privateSignature = Signature.getInstance("SHA256withRSA");
	        privateSignature.initSign(privateKey);
	        privateSignature.update(plainText.getBytes(UTF_8));

	        byte[] signature = privateSignature.sign();

	        return Base64.getEncoder().encodeToString(signature);
	    }

	    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	        Signature publicSignature = Signature.getInstance("SHA256withRSA");
	        publicSignature.initVerify(publicKey);
	        publicSignature.update(plainText.getBytes(UTF_8));

	        byte[] signatureBytes = Base64.getDecoder().decode(signature);

	        return publicSignature.verify(signatureBytes);
	    }

	
}
