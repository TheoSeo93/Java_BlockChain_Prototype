package core;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.List;

import util.Util;

/*
 * This class is where the starting point of the program exists.
 */
public class BlockChainStarter {

	public static void main(String[] args) throws Exception {
		// To get the random private key and public key, define an instance of key pair
		// generator.
		KeyPairGenerator kpg;
		kpg = KeyPairGenerator.getInstance("EC", "SunEC");

		// Generate an elliptic curve (EC) digital signature algorithm.
		ECGenParameterSpec ecsp;
		ecsp = new ECGenParameterSpec("sect163k1");
		// Generate a random key.
		kpg.initialize(ecsp, new SecureRandom());

		// Generate a pair of private key and public key
		KeyPair kp = kpg.genKeyPair();
		PrivateKey privKey = kp.getPrivate();
		PublicKey pubKey = kp.getPublic();

		// Configure a private key using signature object
		Signature ecdsa;
		ecdsa = Signature.getInstance("SHA1withECDSA", "SunEC");
		ecdsa.initSign(privKey);

		// Define a random sentence
		String text = "Person A sends 100 coins to Person B";
		System.out.println("Original text" + text);

		// Derive a signature byte using encryption
		ecdsa.update(text.getBytes("UTF-8"));
		byte[] signatureByte = ecdsa.sign();
		System.out.println("Encrypted Message : 0x" + (new BigInteger(1, signatureByte).toString(16)).toUpperCase());

		// Make sure I can decrypt the message using the public key
		Signature signature;
		signature = Signature.getInstance("SHA1withECDSA","SunEC");
		signature.initVerify(pubKey);

		signature.update(text.getBytes("UTF-8"));
		System.out.println("Verify initial sentence  " + signature.verify(signatureByte));

//		{
//		Block block1 = new Block(1, null, 0, "data", new ArrayList());
//		block1.mine();
//		block1.getInformation();
//
//		Block block2 = new Block(2, block1.getBlockHash(), 0, "data", new ArrayList());
//		block2.addTxn(new Transaction("Person 1", "Person 2", 1.5));
//		block2.addTxn(new Transaction("Person 2", "Person 3", 0.7));
//		block2.mine();
//		block2.getInformation();
//
//		Block block3 = new Block(3, block2.getBlockHash(), 0, "data", new ArrayList());
//		block3.addTxn(new Transaction("Person 4", "Person 5", 7.5));
//		block3.addTxn(new Transaction("Person 5", "Person 1", 0.7));
//		block3.mine();
//
//		block3.getInformation();
//
//		Block block4 = new Block(4, block3.getBlockHash(), 0, "data", new ArrayList());
//		block4.addTxn(new Transaction("Person 1", "Person 2", 1.5));
//		block4.mine();
//		block4.getInformation();
//		}
	}
}
